package edu.olya.tour.utils.database;

/**
 * The class represents an exception to be thrown instead of exceptions
 * caught while retrieving data from database.
 */
public class DaoException extends RuntimeException {

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException (){
        super();
    }

    public DaoException (String message){
        super(message);
    }

    public DaoException (String message, Throwable throwable){
        super(message, throwable);
    }
}
