package httpServer.data;

import httpServer.data.cookies.Cookie;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.enumerations.HttpVersion;

import java.util.Collections;
import java.util.List;

public class HttpRequest {
    private HttpMethod method;
    private String path;
    private HttpVersion version;
    private List<Header> headers;
    private List<Cookie> cookies;
    private List<Parameter> parameters;


    public HttpRequest(HttpMethod method, String path, HttpVersion version, List<Header> headers, List<Cookie> cookies, List<Parameter> parameters) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;
        this.cookies = cookies;
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

    public List<Parameter> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
