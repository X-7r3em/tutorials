package httpServer.exceptions;

public class HttpServerException extends RuntimeException {
    public HttpServerException(String message) {
        super(message);
    }
}
