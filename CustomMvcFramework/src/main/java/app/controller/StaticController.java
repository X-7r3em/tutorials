package app.controller;

import MVCFramework.controller.Controller;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.ErrorResponse;
import httpServer.data.response.FileResponse;
import httpServer.data.response.HttpResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class StaticController extends Controller {
    public HttpResponse favicon(HttpRequest httpRequest) {
        HttpResponse httpResponse = null;
        try {
            File file = new File("src\\main\\resources\\favicon.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileContent = new byte[(int) file.length()];
            fileInputStream.read(fileContent);
            httpResponse = new FileResponse("image/jpg", httpRequest.getSessionId(), fileContent);
        } catch (IOException e) {
            httpResponse = new ErrorResponse("Something went wrong!");
        }

        return httpResponse;
    }
}
