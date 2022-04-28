package exception;

public class PersonTestException extends Exception{
    public PersonTestException() {
        super();
    }

    public PersonTestException(String message) {
        super(message);
    }

    public PersonTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonTestException(Throwable cause) {
        super(cause);
    }

    protected PersonTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
