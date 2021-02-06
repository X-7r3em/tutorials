package httpServer.data.response;

import httpServer.data.Header;

import static util.Constants.*;

public class HtmlResponse extends HttpResponse {
    public HtmlResponse(String sessionId, String body) {
        super(sessionId, body);
        this.addHeader(new Header(CONTENT_TYPE, TEXT_HTML));
        this.addHeader(new Header(CONTENT_LENGTH, String.valueOf(getBody().length)));
    }

    public HtmlResponse(String body) {
        super(body);
        this.addHeader(new Header(CONTENT_TYPE, TEXT_HTML));
        this.addHeader(new Header(CONTENT_LENGTH, String.valueOf(getBody().length)));
    }
}
