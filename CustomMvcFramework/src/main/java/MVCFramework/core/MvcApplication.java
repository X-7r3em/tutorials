package MVCFramework.core;

import MVCFramework.Route;

import java.util.List;

public interface MvcApplication {
    void configureServices();

    void configure(List<Route> routeTable);
}
