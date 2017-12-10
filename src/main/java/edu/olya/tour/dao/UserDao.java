package edu.olya.tour.dao;

import edu.olya.tour.model.User;

import java.sql.SQLException;
import java.util.List;
//Data Access Object Interface defines the standard operations on a model object
public interface UserDao {

    List<User> getUsers();

    User getUser(String u_name);

    int deleteUser(long id);

    int insertUser(String u_name, String u_password);

    boolean userAuthorized(String u_name, String u_password);

    public static class Factory {
        private static UserDao instance = new UserDaoImpl();

        public static UserDao getInstance() {
            return instance;
        }
    }
}
