package MVCFramework.controller;

import MVCFramework.view.ViewEngine;
import MVCFramework.view.ViewEngineImpl;
import app.model.User;
import httpServer.data.request.HttpRequest;
import httpServer.data.response.ErrorResponse;
import httpServer.data.response.HtmlResponse;
import httpServer.data.response.HttpResponse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractController {
    private HttpRequest httpRequest;

    protected HttpResponse view(String path, Object model) {
        HttpResponse httpResponse = null;
        try {
            File file = new File("src\\main\\resources\\" + path + ".html");
            FileReader fileInputStream = new FileReader(file);
            char[] fileContent = new char[(int) file.length()];
            fileInputStream.read(fileContent);
            ViewEngine viewEngine = new ViewEngineImpl();
            String html = String.valueOf(fileContent);
            html = viewEngine.getHtml(html, new User(getUserId(), "", "", ""));
            html = viewEngine.getHtml(html, model);
            httpResponse = new HtmlResponse(html);
            httpResponse.setSessionId(httpRequest.getSessionId());
            httpResponse.setSession(httpRequest.hasSession());
        } catch (IOException e) {
            httpResponse = new ErrorResponse("Something went wrong!");
        }

        return httpResponse;
    }

    protected HttpResponse view(String path) {
        return view(path, null);
    }

    protected void signIn(String userId, String username) {
        httpRequest.addSessionRecord("userId", userId);
        httpRequest.addSessionRecord("username", username);
    }

    protected void signOut(String userId) {
        httpRequest.removeSessionRecord("userId");
        httpRequest.removeSessionRecord("username");
    }

    protected String getUserId() {
        return httpRequest.getSession().get("userId");
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }
}
