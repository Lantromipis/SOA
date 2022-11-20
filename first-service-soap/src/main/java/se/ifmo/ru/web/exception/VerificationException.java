package se.ifmo.ru.web.exception;

import jakarta.xml.ws.WebFault;

@WebFault(name = "VerificationFault")
public class VerificationException extends RuntimeException{
    public VerificationException() {
    }

    public VerificationException(String message) {
        super(message);
    }

    public VerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationException(Throwable cause) {
        super(cause);
    }

    public VerificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
