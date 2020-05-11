package httpServer;

import MVCFramework.Route;
import httpServer.data.HttpRequest;
import httpServer.data.HttpResponse;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.enumerations.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.PORT;
import static util.Constants.TEXT_HTML;

public class HttpServerRunner {
    public static void main(String[] args) {
        List<Route> routeTable = new ArrayList<>();
        routeTable.add(new Route("/", HttpMethod.GET, HttpServerRunner::index));
        routeTable.add(new Route("/", HttpMethod.POST, HttpServerRunner::postIndex));
        routeTable.add(new Route("/home", HttpMethod.GET, HttpServerRunner::home));
        routeTable.add(new Route("/favicon.ico", HttpMethod.GET, HttpServerRunner::favicon));

        HttpServer server = new HttpServer(PORT, routeTable);
        server.start();
    }


    private static HttpResponse favicon(HttpRequest httpRequest) {
        throw new RuntimeException("Favicon method not implemented yet");
    }

    public static HttpResponse index(HttpRequest httpRequest) {
        String content = "<h1>index</h1> <form method=\"POST\"><input type=\"text\" name=\"fname\"><br><br><button>Submit</button></form>";
        HttpResponse httpResponse = new HttpResponse(HttpStatus.OK, new ArrayList<>(), new ArrayList<>());
        httpResponse.setContentType(TEXT_HTML);
        httpResponse.setBody(content.getBytes(StandardCharsets.UTF_8));
        return httpResponse;
    }

    private static HttpResponse postIndex(HttpRequest httpRequest) {
        String content = "<h1>index</h1>";
        HttpResponse httpResponse = new HttpResponse(HttpStatus.OK, new ArrayList<>(), new ArrayList<>());
        httpResponse.setContentType(TEXT_HTML);
        httpResponse.setBody(content.getBytes(StandardCharsets.UTF_8));
        return httpResponse;
    }

    public static HttpResponse home(HttpRequest httpRequest) {
        String content = "<h1>home</h1>";
        HttpResponse httpResponse = new HttpResponse(HttpStatus.OK, new ArrayList<>(), new ArrayList<>());
        httpResponse.setContentType(TEXT_HTML);
        httpResponse.setBody(content.getBytes(StandardCharsets.UTF_8));
        return httpResponse;
    }
}
