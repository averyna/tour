package edu.olya.tour.dao;

import edu.olya.tour.dao.creators.UserRowCreator;
import edu.olya.tour.model.User;
import edu.olya.tour.utils.database.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object class implements UserDao interface
 */
public class UserDaoImpl extends AbstractDAO implements UserDao {

    @Override
    public List<User> getUsers() {
        return executeQuery(
                "SELECT id, name, password, role from users;",
                new UserRowCreator());
    }

    public User getUser(String u_name) {
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        List<User> result = executeQuery(
                "SELECT id, name, password, role from users WHERE users.name = ?;",
                new UserRowCreator(), params);
        if (result.isEmpty()) {
            return null;
        }
        if (result.size() > 1) {
            throw new RuntimeException("Not single result");
        }
        return result.get(0);
    }


    public boolean userAuthorized(String u_name, String u_password) {
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        params.add(u_password);
        List<User> result = executeQuery(
                "SELECT id, name, password, role from users WHERE users.name = ? AND users.password = ?",
                new UserRowCreator() , params);
        if (result.isEmpty()) {
            return false;
        }
        if (result.size() > 1) {
            throw new RuntimeException("Not single result");
        }
        return true;
    }

    @Override
    public User registerUser(User user) {
        insertUser(user.getName(), user.getPassword());
        return getUser(user.getName());
    }

    public int deleteUser(long id) {
        List<Object> params = new ArrayList<>(1);
        params.add(id);
        return executeUpdate("DELETE from users WHERE users.id = ?;", params);
    }

    public int insertUser(String u_name, String u_password) {
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        params.add(u_password);
        return executeUpdate("INSERT  INTO users (name, password) VALUES (?, ?);", params);
    }



}


