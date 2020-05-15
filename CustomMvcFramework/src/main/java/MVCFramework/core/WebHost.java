package MVCFramework.core;

import MVCFramework.Route;
import MVCFramework.annotation.Controller;
import MVCFramework.annotation.GetMapping;
import MVCFramework.annotation.PostMapping;
import MVCFramework.controller.AbstractController;
import httpServer.HttpServer;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.FileResponse;
import httpServer.data.response.HttpResponse;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static util.Constants.PORT;

public class WebHost {
    public static void start(MvcApplication application) {
        List<Route> routeTable = new ArrayList<>();
        ServiceCollection serviceCollection = new ServiceCollectionImpl();
        application.configureServices(serviceCollection);
        application.configure(routeTable);
        autoRegisterStaticRoutes(routeTable);
        autoRegisterActionRoutes(routeTable, serviceCollection);
        HttpServer dispatcher = new HttpServer(PORT, routeTable);
        dispatcher.start();
    }

    private static void autoRegisterActionRoutes(List<Route> routeTable, ServiceCollection serviceCollection) {
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
                    Route route = new Route(path, HttpMethod.GET, action(method, serviceCollection, controllerClass));
                    routeTable.add(route);
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    String path = method.getAnnotation(PostMapping.class).value();
                    Route route = new Route(path, HttpMethod.POST, action(method, serviceCollection, controllerClass));
                    routeTable.add(route);
                }
            }
        }
    }

    private static Function<HttpRequest, HttpResponse> action(
            Method method, ServiceCollection serviceCollection, Class<?> controllerClass) {
        return (httpRequest) -> {
            HttpResponse httpResponse = null;
            try {
                AbstractController controller = (AbstractController) serviceCollection.createInstance(controllerClass);
                controller.setHttpRequest(httpRequest);

                List<Object> parameterValues = new ArrayList<>();
                Parameter[] methodParameters = method.getParameters();
                for (Parameter methodParameter : methodParameters) {
                    if (methodParameter.getType().getName().equals("java.lang.String")) {
                        String parameterName = methodParameter.getName();
                        String value = getValue(httpRequest, parameterName);

                        if (value != null) {
                            parameterValues.add(cast(value, methodParameter.getType()));
                        }
                    } else {
                        try {
                            Object bindingModel = methodParameter.getType().getConstructor().newInstance();
                            List<Method> bindingModelMethods = Arrays.stream(bindingModel.getClass()
                                    .getDeclaredMethods())
                                    .filter(m -> m.getName().startsWith("set"))
                                    .collect(Collectors.toList());
                            for (Method bingingModelMethod : bindingModelMethods) {
                                String parameterName = bingingModelMethod.getName().substring(3);
                                parameterName = parameterName.substring(0, 1).toLowerCase() + parameterName.substring(1);
                                String value = getValue(httpRequest, parameterName);
                                if (value != null) {
                                    bingingModelMethod.invoke(bindingModel, value);
                                }
                            }

                            parameterValues.add(bindingModel);
                        } catch (InstantiationException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }

                httpResponse = (HttpResponse) method.invoke(controller, parameterValues.toArray());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return httpResponse;
        };
    }

    public static <T> T cast(Object o, Class<T> clazz) {
        return clazz.isInstance(o) ? clazz.cast(o) : null;
    }

    private static String getValue(HttpRequest httpRequest, String parameterName) {
        String value = getParameter(httpRequest.getQueryParameters(), parameterName);

        if (value == null) {
            value = getParameter(httpRequest.getBodyParameters(), parameterName);
        }

        return value;
    }

    private static String getParameter(List<httpServer.data.Parameter> parameters, String parameterName) {
        Optional<httpServer.data.Parameter> currentParameter = parameters
                .stream()
                .filter(p -> p.getName().equals(parameterName))
                .findFirst();
        return currentParameter.map(httpServer.data.Parameter::getValue).orElse(null);
    }


    private static void autoRegisterStaticRoutes(List<Route> routeTable) {
        String rootPath = "src\\main\\resources\\static";
        File file = new File(rootPath);
        Queue<File> files = new ArrayDeque<>();

        files.add(file);
        while (!files.isEmpty()) {
            File current = files.poll();
            if (current.isDirectory()) {
                for (File child : Objects.requireNonNull(current.listFiles())) {
                    files.offer(child);
                }
            } else {
                addFileRoute(routeTable, current, rootPath);
            }
        }
    }

    private static void addFileRoute(List<Route> routeTable, File file, String rootPath) {
        String currentPath = file.getPath()
                .replace(rootPath, "")
                .replaceAll("\\\\", "/");

        String extension = currentPath.substring(currentPath.lastIndexOf(".") + 1);

        String contentType = "";
        switch (extension) {
            case "txt":
                extension = "text/plain";
            case "static/css":
                extension = "text/css";
                break;
            case "html":
                extension = "text/html";
                break;
            case "js":
                extension = "text/javascript";
                break;
            case "ico":
                extension = "image/x-icon";
                break;
            case "jpg":
            case "jpeg":
                extension = "image/jpeg";
                break;
            case "png":
                extension = "image/png";
                break;
            case "gif":
                break;
            default:
                extension = "text/plain";
        }


        byte[] currentBody = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(currentBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Function<HttpRequest, HttpResponse> action = httpRequest -> new FileResponse(contentType, null, currentBody);
        Route route = new Route(currentPath, HttpMethod.GET, action);
        routeTable.add(route);
    }
}
