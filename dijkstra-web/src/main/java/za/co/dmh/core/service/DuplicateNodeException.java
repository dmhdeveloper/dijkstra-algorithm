package za.co.dmh.core.service;

public class DuplicateNodeException extends Exception {

    public DuplicateNodeException() {
        super();
    }

    public DuplicateNodeException(String message) {
        super(message);
    }

    public DuplicateNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateNodeException(Throwable cause) {
        super(cause);
    }

    protected DuplicateNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
