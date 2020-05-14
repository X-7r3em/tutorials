package httpServer.data.request;

import httpServer.data.Header;
import httpServer.data.Parameter;
import httpServer.data.cookies.Cookie;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.enumerations.HttpVersion;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    private HttpMethod method;
    private String path;
    private HttpVersion version;
    private List<Header> headers;
    private List<Cookie> cookies;
    private String sessionId;
    private boolean hasSession;
    private Map<String, String> session;
    private List<Parameter> parameters;

    public HttpRequest(HttpMethod method, String path, HttpVersion version,
                       List<Header> headers, List<Cookie> cookies, String sessionId, boolean hasSession, List<Parameter> parameters) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;
        this.cookies = cookies;
        this.sessionId = sessionId;
        this.hasSession = hasSession;
        this.parameters = parameters;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpVersion getVersion() {
        return version;
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    public List<Header> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<Cookie> getCookies() {
        return Collections.unmodifiableList(cookies);
    }

    public void setCookies(List<Cookie> cookies) {
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

    public Map<String, String> getSession() {
        return Collections.unmodifiableMap(session);
    }

    public void setSession(Map<String, String> session) {
        this.session = session;
    }

    public List<Parameter> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void addHeader(Header header) {
        headers.add(header);
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public void addSessionRecord(String name, String value) {
        this.session.put(name, value);
    }

    public void removeSessionRecord(String name) {
        this.session.remove(name);
    }
}
