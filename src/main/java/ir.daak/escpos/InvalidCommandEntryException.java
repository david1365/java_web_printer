package ir.daak.escpos;

import ir.daak.annotation.ResponsesHttpStatus;
import ir.daak.exception.BaseException;
import org.springframework.http.HttpStatus;

@ResponsesHttpStatus(HttpStatus.BAD_REQUEST)
public class InvalidCommandEntryException extends BaseException {
    public InvalidCommandEntryException() {
        super();
    }

    public InvalidCommandEntryException(String message) {
        super(message);
    }

    public InvalidCommandEntryException(Throwable cause) {
        super(cause);
    }

    public InvalidCommandEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandEntryException(String message, String description) {
        super(message, description);
    }

    public InvalidCommandEntryException(String message, String description, Throwable throwable) {
        super(message, description, throwable);
    }

    public InvalidCommandEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
