package httpServer.data.response;

import httpServer.data.Header;
import httpServer.data.cookies.ResponseCookie;
import httpServer.data.enumerations.HttpStatus;
import httpServer.data.enumerations.HttpVersion;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.Constants.NEW_LINE;
import static util.Constants.SYS_SESSION;

public class HttpResponse {
    private HttpVersion version;
    private HttpStatus httpStatus;
    private List<Header> headers;
    private List<ResponseCookie> cookies;
    private String sessionId;
    private boolean hasSession;
    private byte[] body;

    protected HttpResponse(HttpStatus httpStatus, List<Header> headers, List<ResponseCookie> cookies,
                           String sessionId, boolean hasSession, byte[] body) {
        this.sessionId = sessionId;
        this.hasSession = hasSession;
        this.version = HttpVersion.HTTP10;
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.cookies = cookies;
        this.body = body;
    }

    protected HttpResponse() {
        this(HttpStatus.OK, new ArrayList<>(), new ArrayList<>(), null, true, new byte[0]);
    }

    protected HttpResponse(HttpStatus httpStatus, List<Header> headers, List<ResponseCookie> cookies, String sessionId, String body) {
        this(httpStatus, headers, cookies, sessionId, true, body.getBytes(StandardCharsets.UTF_8));
    }

    protected HttpResponse(String body) {
        this(HttpStatus.OK, new ArrayList<>(), new ArrayList<>(), null, body);
    }

    protected HttpResponse(String sessionId, String body) {
        this(HttpStatus.OK, new ArrayList<>(), new ArrayList<>(), sessionId, body);
    }

    protected HttpResponse(byte[] body) {
        this(HttpStatus.OK, new ArrayList<>(), new ArrayList<>(), null, true, body);
    }

    protected HttpResponse(String sessionId, byte[] body) {
        this(HttpStatus.OK, new ArrayList<>(), new ArrayList<>(), sessionId, true, body);
    }

    protected HttpResponse(HttpStatus status, String body) {
        this(status, new ArrayList<>(), new ArrayList<>(), null, body);
    }

    protected HttpResponse(HttpStatus status, String sessionId, String body) {
        this(status, new ArrayList<>(), new ArrayList<>(), sessionId, body);
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean hasSession() {
        return hasSession;
    }

    public void setSession(boolean hasSession) {
        this.hasSession = hasSession;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte[] getBytes() {
        byte[] headerBytes = toString().getBytes(StandardCharsets.UTF_8);
        int size = headerBytes.length + body.length;
        byte[] response = new byte[size];
        System.arraycopy(headerBytes, 0, response, 0, headerBytes.length);
        System.arraycopy(body, 0, response, headerBytes.length, body.length);
        return response;
    }

    public void addHeader(Header header) {
        headers.add(header);
    }

    public void addCookie(ResponseCookie responseCookie) {
        cookies.add(responseCookie);
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append(version.getVersion()).append(" ")
                .append(httpStatus.getCode()).append(" ")
                .append(httpStatus.getMessage()).append(NEW_LINE);

        headers.forEach(h -> response.append(h).append(NEW_LINE));

        if (!hasSession) {
            cookies.add(new ResponseCookie(SYS_SESSION, sessionId));
        }

        cookies.forEach(c -> response.append("Set-Cookie: ").append(c).append(NEW_LINE));

        response.append(NEW_LINE);

        return response.toString();
    }
}