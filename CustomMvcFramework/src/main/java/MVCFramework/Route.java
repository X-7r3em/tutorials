package MVCFramework;

import httpServer.data.request.HttpRequest;
import httpServer.data.response.HttpResponse;
import httpServer.data.enumerations.HttpMethod;

import java.util.function.Function;

public class Route {
    private String path;
    private HttpMethod method;
    private Function<HttpRequest, HttpResponse> action;

    public Route(String path, HttpMethod method, Function<HttpRequest, HttpResponse> action) {
        this.path = path;
        this.method = method;
        this.action = action;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Function<HttpRequest, HttpResponse> getAction() {
        return action;
    }

    public void setAction(Function<HttpRequest, HttpResponse> action) {
        this.action = action;
    }
}
