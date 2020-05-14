package MVCFramework.core;

import MVCFramework.Route;
import httpServer.HttpServer;
import httpServer.data.enumerations.HttpMethod;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.FileResponse;
import httpServer.data.response.HttpResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import static util.Constants.PORT;

public class WebHost {
    public static void start(MvcApplication application) {
        List<Route> routeTable = new ArrayList<>();
        application.configureServices();
        application.configure(routeTable);
        autoRegisterRoutes(routeTable);
        HttpServer dispatcher = new HttpServer(PORT, routeTable);
        dispatcher.start();
    }

    private static void autoRegisterRoutes(List<Route> routeTable) {
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
