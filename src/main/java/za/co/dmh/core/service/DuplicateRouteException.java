package za.co.dmh.core.service;

public class DuplicateRouteException extends Exception {

    public DuplicateRouteException() {
        super();
    }

    public DuplicateRouteException(String message) {
        super(message);
    }

    public DuplicateRouteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRouteException(Throwable cause) {
        super(cause);
    }

    protected DuplicateRouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
