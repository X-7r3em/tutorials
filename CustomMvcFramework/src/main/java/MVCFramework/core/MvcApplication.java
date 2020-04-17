package MVCFramework.core;

import MVCFramework.Route;

import java.util.List;

public interface MvcApplication {
    void configure(List<Route> routeTable);

    void configureServices(ServiceCollection serviceCollection);
}
