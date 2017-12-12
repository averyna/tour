package edu.olya.tour.service;

import edu.olya.tour.dao.UserDao;
import edu.olya.tour.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{

    public List<User> getUsers(){
        return UserDao.Factory.getInstance().getUsers();
    }

    public User getUser(String u_name) {
        return UserDao.Factory.getInstance().getUser(u_name);
    }

    public int deleteUser(long id){
        return UserDao.Factory.getInstance().deleteUser(id);
    }

    public int insertUser(String u_name, String u_password) {
        return UserDao.Factory.getInstance().insertUser(u_name, u_password);
    }
    public boolean userAuthorized(String u_name, String u_password){
        return UserDao.Factory.getInstance().userAuthorized(u_name, u_password);
    }
}
