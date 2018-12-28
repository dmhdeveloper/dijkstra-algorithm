package za.co.dmh.core.domain.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    public Status status;
    public String message;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
