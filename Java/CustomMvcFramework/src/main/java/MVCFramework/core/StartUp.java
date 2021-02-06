package MVCFramework.core;

import MVCFramework.Route;
import app.service.UserService;
import app.service.UserServiceImpl;

import java.util.List;

public class StartUp implements MvcApplication {

    @Override
    public void configure(List<Route> routeTable) {

    }

    @Override
    public void configureServices(ServiceCollection serviceCollection) {
        serviceCollection.add(UserService.class, UserServiceImpl.class);
    }
}
