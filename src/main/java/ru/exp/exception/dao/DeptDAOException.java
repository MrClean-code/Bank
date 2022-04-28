package ru.exp.exception.dao;

public class DeptDAOException extends Exception {

    public DeptDAOException() {
        super();
    }

    public DeptDAOException(String message) {
        super(message);
    }

    public DeptDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeptDAOException(Throwable cause) {
        super(cause);
    }

    protected DeptDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
