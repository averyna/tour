package edu.olya.tour.dao;

import edu.olya.tour.model.User;
import edu.olya.tour.utils.database.AbstractDAO;
import edu.olya.tour.utils.database.ConnectionHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object class implements UserDao interface
 */
public class UserDaoImpl extends AbstractDAO implements UserDao {

    /**
     * searches all users
     *
     * @return returns list of users
     */
    @Override
    public List<User> getUsers() {
        return executeQuery(
                "SELECT id, name, password, role from users;",
                new RowCreator<User>() {
                    @Override
                    public User buildRow(ResultSet rs) throws SQLException {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        String password = rs.getString(3);
                        String role = rs.getString(4);
                        return new User(id, name, password, role);
                    }
                }
        );
    }

    /**
     * searches user by user name
     *
     * @param u_name user name
     * @return returns user object
     */
    public User getUser(String u_name) {
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        List<User> result = executeQuery(
                "SELECT id, name, password, role from users WHERE users.name = ?;",
                new RowCreator<User>() {
                    @Override
                    public User buildRow(ResultSet rs) throws SQLException {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        String password = rs.getString(3);
                        String role = rs.getString(4);
                        return new User(id, name, password, role);
                    }
                }, params
        );
        if (result.isEmpty()) {
            return null;
        }
        if (result.size() > 1) {
            throw new RuntimeException("Not single result");
        }
        return result.get(0);
    }

    /**
     * Method determines whether user with specified name and password exists in database
     *
     * @param u_name     user name
     * @param u_password user password
     * @return returns users id if the User exists in database, otherwise returns 0
     */
    public boolean userAuthorized(String u_name, String u_password) {
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        params.add(u_password);
        List<User> result = executeQuery(
                "SELECT id, name, password, role from users WHERE users.name = ? AND users.password = ?",
                new RowCreator<User>() {
                    @Override
                    public User buildRow(ResultSet rs) throws SQLException {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        String password = rs.getString(3);
                        String role = rs.getString(4);
                        return new User(id, name, password, role);
                    }
                }, params
        );
        if (result.isEmpty()) {
            return false;
        }
        if (result.size() > 1) {
            throw new RuntimeException("Not single result");
        }
        return true;
    }

    /**
     * Deletes user with specified id parameter
     *
     * @param id user id from database
     */
    public int deleteUser(long id) {
        List<Object> params = new ArrayList<>(1);
        params.add(id);
        return executeUpdate("DELETE from users WHERE users.id = ?;", params);
    }

    /**
     * Inserts user
     *
     * @param u_name     user name
     * @param u_password user password
     */
    public int insertUser(String u_name, String u_password) {
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        params.add(u_password);
        return executeUpdate("INSERT  INTO users (name, password) VALUES (?, ?);", params);
    }

}


