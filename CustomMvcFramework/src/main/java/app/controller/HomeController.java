package app.controller;

import MVCFramework.annotation.Controller;
import MVCFramework.annotation.GetMapping;
import MVCFramework.annotation.PostMapping;
import MVCFramework.controller.AbstractController;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.HttpResponse;
import httpServer.data.response.RedirectResponse;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class HomeController extends AbstractController {

    @GetMapping("/")
    public HttpResponse index(HttpRequest httpRequest) {
        return view("index");
    }

    @GetMapping("/home")
    public HttpResponse home(HttpRequest httpRequest) {
        TestObject testObject = new TestObject("Vasil", true, new ArrayList<>(Arrays.asList(1, 2, 3)));
        return view("home", testObject);
    }

    @PostMapping("/")
    public HttpResponse postIndex(HttpRequest httpRequest) {
        return new RedirectResponse("/");
    }

    @GetMapping("/redirect")
    public HttpResponse redirect(HttpRequest httpRequest) {
        return new RedirectResponse("/");
    }
}
