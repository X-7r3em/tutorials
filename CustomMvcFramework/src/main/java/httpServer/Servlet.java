package httpServer;

import MVCFramework.Route;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.ErrorResponse;
import httpServer.data.response.HtmlResponse;
import httpServer.data.response.HttpResponse;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

import static util.Constants.ANONYMOUS;
import static util.Constants.NEW_LINE;

public class Servlet extends Thread {
    private static final Map<String, Map<String, String>> sessionData = new HashMap<>();
    private final Socket socket;
    private final List<Route> routeTable;

    public Servlet(Socket socket, List<Route> routeTable) {
        this.socket = socket;
        this.routeTable = routeTable;
    }

    @Override
    public void run() {
        HttpResponse httpResponse = null;
        try {
            HttpRequestParser requestParser = new HttpRequestParser();
            HttpRequest httpRequest = requestParser.parse(socket.getInputStream());

            String sessionId = null;
            if (!sessionData.containsKey(httpRequest.getSessionId())) {
                sessionId = UUID.randomUUID().toString();
                Map<String, String> session = new HashMap<>();
                session.put("Username", ANONYMOUS);
                sessionData.put(sessionId, session);
            }

            httpRequest.setSessionId(sessionId);
            httpRequest.setSession(sessionData.get(httpRequest.getSessionId()));

            Optional<Route> route = routeTable.stream()
                    .filter(r -> r.getPath().equals(httpRequest.getPath()) && r.getMethod() == httpRequest.getMethod())
                    .findFirst();

            if (route.isPresent()) {
                httpResponse = route.get().getAction().apply(httpRequest);
            } else {
                httpResponse = new HtmlResponse(null, "No such route!");
            }

            System.out.println(String.format("%s %s", httpRequest.getMethod(), httpRequest.getPath()));
        } catch (Exception ex) {
            String stackTrace = Arrays.stream(ex.getStackTrace())
                    .map(Object::toString)
                    .collect(Collectors.joining(NEW_LINE));
            stackTrace += NEW_LINE + ex.getMessage() + NEW_LINE;
            httpResponse = new ErrorResponse(stackTrace);
        } finally {
            try {
                if (socket != null) {
                    if (httpResponse != null) {
                        socket.getOutputStream().write(httpResponse.getBytes());
                    }
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
