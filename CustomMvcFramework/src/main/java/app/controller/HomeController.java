package app.controller;

import MVCFramework.controller.Controller;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.HttpResponse;
import httpServer.data.response.RedirectResponse;

public class HomeController extends Controller {
    public HttpResponse index(HttpRequest httpRequest) {
        return view("index");
    }

    public HttpResponse home(HttpRequest httpRequest) {
        return view("home");
    }

    public HttpResponse postIndex(HttpRequest httpRequest) {
        return new RedirectResponse("/");
    }

    public HttpResponse redirect(HttpRequest httpRequest) {
        return new RedirectResponse("/");
    }
}
