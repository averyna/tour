package edu.olya.tour.utils.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class provides methods to obtain connection through a {@code DataSource}
 * object and to close this connection.
 */
public class ConnectionManager {

    private static DataSource APPLICATION_DATASOURCE;


    public static Connection getConnection() throws SQLException {
        if(APPLICATION_DATASOURCE==null) {
            try {
                Context jndi =  new InitialContext();
                APPLICATION_DATASOURCE = (DataSource) jndi.lookup("java:/comp/env/jdbc/tour");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
        return APPLICATION_DATASOURCE.getConnection();
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
