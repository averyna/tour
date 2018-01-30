package edu.olya.tour.utils.database;

import java.sql.Connection;

/**
 * This class contains thread-local variable to hold connection to database
 * and provides methods to set this connection and get it.
 */
public class ConnectionHolder {
    private static ThreadLocal<Connection> THREAD_CONTEXT = new ThreadLocal<Connection>();

    /**
     * Method returns an object stored in {@code THREAD_CONTEXT} variable
     * @return the object of type {@code Connection}
     */
    public static Connection getConnection() {
        return THREAD_CONTEXT.get();
    }

    /**
     * Method assigns {@code param} value to {@code THREAD_CONTEXT} variable
     * @param connection an object to be stored in local thread
     */
    public static void setConnection(Connection connection) {
        THREAD_CONTEXT.set(connection);
    }
}
