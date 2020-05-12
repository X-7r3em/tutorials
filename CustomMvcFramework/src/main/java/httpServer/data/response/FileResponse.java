package httpServer.data.response;

import httpServer.data.Header;

import static util.Constants.CONTENT_LENGTH;
import static util.Constants.CONTENT_TYPE;

public class FileResponse extends HttpResponse {
    public FileResponse(String contentType, String sessionId, byte[] body) {
        super(sessionId, body);
        this.addHeader(new Header(CONTENT_TYPE, contentType));
        this.addHeader(new Header(CONTENT_LENGTH, String.valueOf(getBody().length)));
    }
}
