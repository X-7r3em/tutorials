package MVCFramework.controller;

import MVCFramework.annotation.Controller;
import MVCFramework.view.ViewEngine;
import MVCFramework.view.ViewEngineImpl;
import httpServer.data.response.ErrorResponse;
import httpServer.data.response.HtmlResponse;
import httpServer.data.response.HttpResponse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractController {
    protected HttpResponse view(String path, Object model) {
        HttpResponse httpResponse = null;
        try {
            File file = new File("src\\main\\resources\\" + path + ".html");
            FileReader fileInputStream = new FileReader(file);
            char[] fileContent = new char[(int) file.length()];
            fileInputStream.read(fileContent);
            ViewEngine viewEngine = new ViewEngineImpl();
            String html = String.valueOf(fileContent);
            html = viewEngine.getHtml(html, model);
            httpResponse = new HtmlResponse(html);
        } catch (IOException e) {
            httpResponse = new ErrorResponse("Something went wrong!");
        }

        return httpResponse;
    }

    protected HttpResponse view(String path) {
        return view(path, null);
    }
}
