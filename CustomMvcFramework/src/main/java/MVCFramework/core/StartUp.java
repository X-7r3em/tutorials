package MVCFramework.core;

import MVCFramework.Route;
import app.controller.ContactController;
import app.controller.HomeController;
import app.controller.StaticController;
import httpServer.data.enumerations.HttpMethod;

import java.util.List;

public class StartUp implements MvcApplication {
    @Override
    public void configure(List<Route> routeTable) {
        routeTable.add(new Route("/", HttpMethod.GET, new HomeController()::index));
        routeTable.add(new Route("/", HttpMethod.POST, new HomeController()::postIndex));
        routeTable.add(new Route("/home", HttpMethod.GET, new HomeController()::home));
        routeTable.add(new Route("/contact", HttpMethod.GET, new ContactController()::contact));
        routeTable.add(new Route("/redirect", HttpMethod.GET, new HomeController()::redirect));
        routeTable.add(new Route("/favicon.ico", HttpMethod.GET, new StaticController()::favicon));
    }

    @Override
    public void configureServices() {

    }
}
