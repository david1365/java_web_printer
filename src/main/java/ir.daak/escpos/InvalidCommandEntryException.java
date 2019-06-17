package ir.daak.escpos;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

import ir.daak.base.annotation.ResponsesHttpStatus;
import ir.daak.base.exception.BaseException;
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
