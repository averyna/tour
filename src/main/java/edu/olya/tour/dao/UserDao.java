package edu.olya.tour.dao;

import edu.olya.tour.model.User;

import java.util.List;

public interface UserDao {
    /**
     * searches all users
     *
     * @return returns list of users
     */
    List<User> getUsers();

    /**
     * searches user by user name
     *
     * @param u_name user name
     * @return returns user object
     */
    User getUser(String u_name);

    /**
     * Deletes user with specified id parameter
     *
     * @param id user id from database
     */
    int deleteUser(long id);

    /**
     * Inserts user
     *
     * @param u_name     user name
     * @param u_password user password
     */
    int insertUser(String u_name, String u_password);

    /**
     * Method determines whether user with specified name and password exists in database
     *
     * @param u_name     user name
     * @param u_password user password
     * @return returns users id if the User exists in database, otherwise returns 0
     */
    boolean userAuthorized(String u_name, String u_password);

    User registerUser(User user);

    public static class Factory {
        private static UserDao instance = new UserDaoImpl();

        public static UserDao getInstance() {
            return instance;
        }
    }
}
