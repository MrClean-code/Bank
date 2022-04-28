package exception;

public class DeptTestException extends Exception{

    public DeptTestException() {
    }

    public DeptTestException(String message) {
        super(message);
    }

    public DeptTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeptTestException(Throwable cause) {
        super(cause);
    }

    public DeptTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void printStackTrace(String s) {
    }
}
