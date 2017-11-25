package edu.olya.tour.service;

import edu.olya.tour.utils.database.TransactionManager;
import edu.olya.tour.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUser(String u_name);
    int deleteUser(long id);
    int insertUser(String u_name, String u_password);
    boolean existsUser(String u_name);
    boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException;

    public static class Factory {
        private static UserService instance;

        public static UserService getInstance() {
            if (instance == null) {
                instance = TransactionManager.wrap(new UserServiceImpl()); //возвращается proxy, приведенный к типу UserServiceImpl
            }
            return instance;
        }
    }
}
