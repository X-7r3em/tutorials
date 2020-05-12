package app.controller;

import MVCFramework.controller.Controller;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.HtmlResponse;
import httpServer.data.response.HttpResponse;

public class ContactController extends Controller {
    public HttpResponse contact(HttpRequest httpRequest) {
        return view("contact");
    }
}
