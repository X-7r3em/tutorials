package MVCFramework.core;

import MVCFramework.Route;
import httpServer.HttpServer;

import java.util.ArrayList;
import java.util.List;

import static util.Constants.PORT;

public class WebHost {
    public static void start(MvcApplication application) {
        List<Route> routeTable = new ArrayList<>();
        application.configureServices();
        application.configure(routeTable);
        HttpServer dispatcher = new HttpServer(PORT, routeTable);
        dispatcher.start();
    }
}
