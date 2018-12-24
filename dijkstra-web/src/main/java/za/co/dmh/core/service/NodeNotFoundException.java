package za.co.dmh.core.service;

public class NodeNotFoundException extends Exception {

    public NodeNotFoundException() {
        super();
    }

    public NodeNotFoundException(String message) {
        super(message);
    }

    public NodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected NodeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
