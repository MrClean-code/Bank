package ru.exp.exception.dao;

public class OfferDAOException extends Exception {

    public OfferDAOException() {
        super();
    }

    public OfferDAOException(String message) {
        super(message);
    }

    public OfferDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferDAOException(Throwable cause) {
        super(cause);
    }

    protected OfferDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
