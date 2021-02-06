package httpServer.data.response;

import httpServer.data.Header;
import httpServer.data.enumerations.HttpStatus;

import static util.Constants.*;

public class ErrorResponse extends HttpResponse {
        public ErrorResponse(String body) {
            super(HttpStatus.INTERNAL_SERVER_ERROR, body);
            this.addHeader(new Header(CONTENT_TYPE, TEXT_HTML));
            this.addHeader(new Header(CONTENT_LENGTH, String.valueOf(getBody().length)));
    }
}
