package MVCFramework.core;

import MVCFramework.Route;
import MVCFramework.annotation.Controller;
import MVCFramework.annotation.GetMapping;
import MVCFramework.annotation.PostMapping;
import MVCFramework.controller.AbstractController;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.HttpResponse;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class StartUp implements MvcApplication {

    @Override
    public void configure(List<Route> routeTable) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(""))
                        .setScanners(new SubTypesScanner(),
                                new TypeAnnotationsScanner(),
                                new MethodAnnotationsScanner())
        );

        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);

        for (Class<?> controllerClass : controllers) {
            for (Method method : controllerClass.getMethods()) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    String path = method.getAnnotation(GetMapping.class).value();
                    Route route = new Route(path, HttpMethod.GET, this.action(method, controllerClass));
                    routeTable.add(route);
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    String path = method.getAnnotation(PostMapping.class).value();
                    Route route = new Route(path, HttpMethod.POST, this.action(method, controllerClass));
                    routeTable.add(route);
                }
            }
        }
    }

    private Function<HttpRequest, HttpResponse> action(Method method, Class<?> controllerClass) {
        return (httpRequest) -> {
            HttpResponse httpResponse = null;
            try {
                AbstractController controller = (AbstractController) controllerClass
                        .getConstructor()
                        .newInstance();
                controller.setHttpRequest(httpRequest);
                httpResponse = (HttpResponse) method.invoke(controller);
            } catch (IllegalAccessException
                    | InvocationTargetException
                    | NoSuchMethodException
                    | InstantiationException e) {
                e.printStackTrace();
            }

            return httpResponse;
        };
    }

    @Override
    public void configureServices() {

    }
}
