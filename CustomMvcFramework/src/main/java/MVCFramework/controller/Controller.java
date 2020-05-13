package MVCFramework.controller;

import MVCFramework.view.ViewEngine;
import MVCFramework.view.ViewEngineImpl;
import app.controller.TestObject;
import httpServer.data.Header;
import httpServer.data.response.ErrorResponse;
import httpServer.data.response.HtmlResponse;
import httpServer.data.response.HttpResponse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Controller {
    protected HttpResponse view(String path) {
        HttpResponse httpResponse = null;
        try {
            File file = new File("src\\main\\resources\\" + path + ".html");
            FileReader fileInputStream = new FileReader(file);
            char[] fileContent = new char[(int) file.length()];
            fileInputStream.read(fileContent);
            String html = String.valueOf(fileContent);
            ViewEngine viewEngine = new ViewEngineImpl();
            TestObject testObject = new TestObject("Vasil", new ArrayList<>(Arrays.asList(1, 2, 3)));
            html = viewEngine.getHtml(html, testObject);
            httpResponse = new HtmlResponse(html);
        } catch (IOException e) {
            httpResponse = new ErrorResponse("Something went wrong!");
        }

        return httpResponse;
    }
}
