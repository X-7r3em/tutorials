package httpServer;

import httpServer.data.Header;
import httpServer.data.Parameter;
import httpServer.data.cookies.Cookie;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.enumerations.HttpVersion;
import httpServer.data.request.HttpRequest;
import httpServer.exceptions.HttpServerException;
import org.apache.commons.text.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static util.Constants.*;

public class HttpRequestParser {
    public HttpRequest parse(InputStream input) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String line = bufferedReader.readLine();
        String[] responseTokens = line.split(" ");
        if (responseTokens.length != 3) {
            throw new HttpServerException(String.format("Invalid HTTP request! %s", line));
        }

        String methodStr = responseTokens[0];
        String pathAndQueryString = responseTokens[1];
        String path = "";
        String queryParametersText = "";
        if (pathAndQueryString.contains("?")) {
           String[] pathAndQueryStringTokens = pathAndQueryString.split("\\?", 2);
           path = pathAndQueryStringTokens[0];
           queryParametersText = pathAndQueryStringTokens[1];
        } else {
            path = pathAndQueryString;
        }

        String protocolVersionStr = responseTokens[2];
        HttpMethod method = HttpMethod.valueOf(methodStr.toUpperCase());
        HttpVersion version = parseHttpVersion(protocolVersionStr);

        List<Header> headers = new ArrayList<>();
        List<Cookie> cookies = new ArrayList<>();
        int contentLength = 0;
        while (!(line = bufferedReader.readLine()).isEmpty()) {
            String[] tokens = line.split(": ", 2);
            String name = tokens[0].toUpperCase();
            String value = tokens[1];
            if (CONTENT_LENGTH.equalsIgnoreCase(name)) {
                contentLength = Integer.parseInt(tokens[1]);
            }

            headers.add(new Header(name, value));

            if ("cookie".equalsIgnoreCase(name)) {
                Arrays.stream(value.split("; "))
                        .map(c -> {
                            String[] cookieTokens = c.split("=", 2);
                            return new Cookie(cookieTokens[0], cookieTokens[1]);
                        })
                        .forEach(cookies::add);
            }
        }

        String sessionId = cookies.stream()
                .filter(c -> c.getName().equalsIgnoreCase(SYS_SESSION))
                .findFirst().orElseGet(() -> new Cookie(SYS_SESSION, null))
                .getValue();

        char[] bodyParametersBuffer = new char[contentLength];
        List<Parameter> bodyParameters = new ArrayList<>();
        if (contentLength > 0) {
            bufferedReader.read(bodyParametersBuffer, 0, contentLength);
            String paramStr = URLDecoder.decode(new String(bodyParametersBuffer), StandardCharsets.UTF_8);
            bodyParameters = parseParameters(paramStr);
        }

        List<Parameter> queryParameters = parseParameters(queryParametersText);

        return new HttpRequest(method, path, version, headers, cookies, sessionId, true, bodyParameters, queryParameters);
    }

    private List<Parameter> parseParameters(String parameters) {
        String escapedParameters = StringEscapeUtils.unescapeHtml4(parameters);
        return Arrays.stream(escapedParameters.split("&"))
                .filter(s -> !s.isEmpty())
                .map(p -> {
                    String[] parameterTokens = p.split("=", 2);
                    return new Parameter(parameterTokens[0], parameterTokens[1]);
                })
                .collect(Collectors.toList());
    }

    private HttpVersion parseHttpVersion(String version) {
        HttpVersion httpVersion = null;
        switch (version) {
            case "HTTP/1.0":
                httpVersion = HttpVersion.HTTP10;
                break;
            case "HTTP/1.1":
                httpVersion = HttpVersion.HTTP11;
                break;
            case "HTTP/2.0":
                httpVersion = HttpVersion.HTTP20;
                break;
            default:
                throw new HttpServerException("Invalid HTTP protocol.");
        }

        return httpVersion;
    }
}
