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

    private static Function<HttpRequest, HttpResponse> action(
            Method method, ServiceCollection serviceCollection, Class<?> controllerClass) {
        return (httpRequest) -> {
            HttpResponse httpResponse = null;
            try {
                AbstractController controller = (AbstractController) serviceCollection.createInstance(controllerClass);
                controller.setHttpRequest(httpRequest);

                Parameter[] methodParameters = method.getParameters();
                Object[] arguments = new Object[methodParameters.length];

                for (int i = 0; i < methodParameters.length; i++) {
                    arguments[i] =
                            getValue(methodParameters[i].getName(), methodParameters[i].getType(), httpRequest);
                }

                try {
                    httpResponse = (HttpResponse) method.invoke(controller, arguments);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return httpResponse;
        };
    }

    private static String getRequestValue(HttpRequest httpRequest, String parameterName) {
        String value = getParameter(httpRequest.getQueryParameters(), parameterName);

        if (value == null) {
            value = getParameter(httpRequest.getBodyParameters(), parameterName);
        }

        return value;
    }

    public static Object getValue(String parameterName, Class<?> parameterType, HttpRequest httpRequest) {
        Object value = null;
        if (isPrimitive(parameterType)) {
            String requestValue = getRequestValue(httpRequest, parameterName);
            value = cast(parameterType, requestValue);
        } else {
            try {
                Object bindingModel = parameterType.getConstructor().newInstance();
                List<Method> bindingModelMethods = Arrays.stream(bindingModel.getClass()
                        .getDeclaredMethods())
                        .filter(m -> m.getName().startsWith("set"))
                        .collect(Collectors.toList());
                for (Method bingingModelMethod : bindingModelMethods) {
                    String modelParameterName = getSetterFieldName(bingingModelMethod.getName());
                    Class<?> modelParameterType = bingingModelMethod.getParameterTypes()[0];
                    if (isPrimitive(modelParameterType)) {
                        String requestValue = getRequestValue(httpRequest, modelParameterName);
                        bingingModelMethod.invoke(bindingModel, cast(modelParameterType, requestValue));
                    } else {
                        bingingModelMethod.invoke(bindingModel, getValue(modelParameterName, modelParameterType, httpRequest));
                    }
                }

                value = bindingModel;
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
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

    private static String getSetterFieldName(String setterName) {
        String parameterName = setterName.substring(3);
        return parameterName.substring(0, 1).toLowerCase() + parameterName.substring(1);
    }


    public static Object cast(Class<?> type, String value) {
        String typeName = type.getName();
        switch (typeName) {
            case "java.lang.String": {
                return value;
            }
            case "byte":
            case "java.lang.Byte": {
                return Byte.parseByte(value);
            }
            case "short":
            case "java.lang.Short": {
                return Short.parseShort(value);
            }
            case "int":
            case "java.lang.Integer": {
                return Integer.parseInt(value);
            }
            case "long":
            case "java.lang.Long": {
                return Long.parseLong(value);
            }
            case "char":
            case "java.lang.Character": {
                return value.charAt(0);
            }
            case "boolean":
            case "java.lang.Boolean": {
                return Boolean.parseBoolean(value);
            }
        }

        return null;
    }

    public static boolean isPrimitive(Class<?> type) {
        String typeName = type.getName();
        return typeName.equals("java.lang.String")
                || typeName.equals("byte")
                || typeName.equals("java.lang.Byte")
                || typeName.equals("short")
                || typeName.equals("java.lang.Short")
                || typeName.equals("int")
                || typeName.equals("java.lang.Integer")
                || typeName.equals("long")
                || typeName.equals("java.lang.Long")
                || typeName.equals("char")
                || typeName.equals("java.lang.Character")
                || typeName.equals("boolean")
                || typeName.equals("java.lang.Boolean");
    }
}
