package httpServer.data;

import httpServer.data.cookies.ResponseCookie;
import httpServer.data.enumerations.HttpVersion;
import httpServer.data.enumerations.HttpStatus;

import java.util.Collections;
import java.util.List;

import static util.Constants.*;

public class HttpResponse {
    private HttpVersion version;
    private HttpStatus httpStatus;
    private String contentType;
    private List<Header> headers;
    private List<ResponseCookie> cookies;
    private byte[] body;

    public HttpResponse(HttpStatus httpStatus, List<Header> headers, List<ResponseCookie> cookies) {
        this.version = HttpVersion.HTTP10;
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.cookies = cookies;
    }

    public HttpVersion getVersion() {
        return version;
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<Header> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<ResponseCookie> getCookies() {
        return Collections.unmodifiableList(cookies);
    }

    public void setCookies(List<ResponseCookie> cookies) {
        this.cookies = cookies;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append(version.getVersion()).append(" ")
                .append(httpStatus.getCode()).append(" ")
                .append(httpStatus.getMessage()).append(NEW_LINE);

        headers.forEach(h -> response.append(h).append(NEW_LINE));
        cookies.forEach(c -> response.append("Set-Cookie: ").append(c).append(NEW_LINE));
        response.append(NEW_LINE);

        return response.toString();
    }
}
