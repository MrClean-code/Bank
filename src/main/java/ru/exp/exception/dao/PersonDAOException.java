package ru.exp.exception.dao;

public class PersonDAOException extends Exception {

    public PersonDAOException() {
        super();
    }

    public PersonDAOException(String message) {
        super(message);
    }

    public PersonDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonDAOException(Throwable cause) {
        super(cause);
    }

    protected PersonDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
