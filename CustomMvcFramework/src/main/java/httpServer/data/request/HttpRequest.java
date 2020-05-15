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
    private List<Parameter> bodyParameters;
    private List<Parameter> queryParameters;

    public HttpRequest(HttpMethod method, String path, HttpVersion version,
                       List<Header> headers, List<Cookie> cookies, String sessionId, boolean hasSession,
                       List<Parameter> bodyParameters, List<Parameter> queryParameters) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;
        this.cookies = cookies;
        this.sessionId = sessionId;
        this.hasSession = hasSession;
        this.bodyParameters = bodyParameters;
        this.queryParameters = queryParameters;
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


    public List<Parameter> getBodyParameters() {
        return Collections.unmodifiableList(bodyParameters);
    }

    public void setBodyParameters(List<Parameter> bodyParameters) {
        this.bodyParameters = bodyParameters;
    }

    public List<Parameter> getQueryParameters() {
        return Collections.unmodifiableList(queryParameters);
    }

    public void setQueryParameters(List<Parameter> queryParameters) {
        this.queryParameters = queryParameters;
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
