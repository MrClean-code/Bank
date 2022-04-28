package exception;

public class OfferTestException extends Exception {
    public OfferTestException() {
    }

    public OfferTestException(String message) {
        super(message);
    }

    public OfferTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferTestException(Throwable cause) {
        super(cause);
    }

    public OfferTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
