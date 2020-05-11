package httpServer;

import MVCFramework.Route;
import httpServer.data.Header;
import httpServer.data.HttpRequest;
import httpServer.data.HttpResponse;
import httpServer.data.enumerations.HttpStatus;
import httpServer.exceptions.HttpServerException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static util.Constants.*;

public class Servlet extends Thread {
    private final Socket socket;
    private final List<Route> routeTable;

    public Servlet(Socket socket, List<Route> routeTable) {
        this.socket = socket;
        this.routeTable = routeTable;
    }

    @Override
    public void run() {
        BufferedWriter out = null;
        HttpResponse httpResponse = null;
        try {
            HttpRequestParser requestParser = new HttpRequestParser();
            HttpRequest httpRequest = requestParser.parse(socket.getInputStream());

            Optional<Route> route = routeTable.stream()
                    .filter(r -> r.getPath().equals(httpRequest.getPath()) && r.getMethod() == httpRequest.getMethod())
                    .findFirst();

            if (route.isPresent()) {
                httpResponse = route.get().getAction().apply(httpRequest);
            } else {
                throw new HttpServerException("No such route!");
            }

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            out.write(httpResponse.toString());

            httpResponse.setContentType(TEXT_HTML);
            switch (httpResponse.getContentType()) {
                case TEXT_PLAIN:
                case TEXT_HTML:
                    out.write(new String(httpResponse.getBody(), StandardCharsets.UTF_8));
                    break;
            }
        } catch (Exception ex) {
            List<Header> headers = new ArrayList<>(Collections.singleton(new Header(CONTENT_TYPE, TEXT_PLAIN)));
            httpResponse = new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, headers, new ArrayList<>());
            try {
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                out.write(httpResponse.toString());
                String stackTrace = Arrays.stream(ex.getStackTrace())
                        .map(Object::toString)
                        .collect(Collectors.joining("\r\n"));
                out.write(stackTrace);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
