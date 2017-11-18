package edu.olya.tour.utils.database;

import java.sql.Connection;

public class ConnectionHolder {
    private static ThreadLocal<Connection> THREAD_CONTEXT = new ThreadLocal<Connection>();

    public static Connection getConnection() {
        return THREAD_CONTEXT.get();
    }

    public static void setConnection(Connection connection) {
        THREAD_CONTEXT.set(connection);
    }
}
