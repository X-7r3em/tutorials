package httpServer.data.response;

import httpServer.data.Header;
import httpServer.data.enumerations.HttpStatus;

public class RedirectResponse extends HttpResponse {
    public RedirectResponse(String newLocation) {
        this.setHttpStatus(HttpStatus.FOUND);
        this.addHeader(new Header("Location", newLocation));
    }
}
