package edu.olya.tour.dao;

import edu.olya.tour.model.User;

import java.sql.SQLException;
import java.util.List;
//Data Access Object Interface defines the standard operations on a model object
public interface UserDao {

    List<User> getUsers()throws ClassNotFoundException, SQLException;

    User getUser(String u_name) throws ClassNotFoundException, SQLException;

    void deleteUser(String u_name)throws ClassNotFoundException, SQLException;

    void addUser(String u_name, String u_password)throws ClassNotFoundException, SQLException;

    boolean existsUser(String u_name)throws ClassNotFoundException, SQLException;

    boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException;

    public static class Factory {
        private static UserDao instance = new UserDaoImpl();

        public static UserDao getInstance() {
            return instance;
        }
    }
}
