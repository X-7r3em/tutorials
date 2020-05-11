package httpServer;

import MVCFramework.Route;

import java.io.*;
import java.net.ServerSocket;
import java.util.List;

public class HttpServer {
    private final int port;
    private final List<Route> routeTable;

    public HttpServer(int port, List<Route> routeTable) {
        this.port = port;
        this.routeTable = routeTable;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                try {
                    new Servlet(serverSocket.accept(), routeTable).start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
