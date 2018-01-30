package edu.olya.tour.service;

import edu.olya.tour.dao.UserDao;
import edu.olya.tour.model.User;
import java.util.List;

public class UserServiceImpl implements UserService{

    @Override
    public List<User> getUsers(){
        return UserDao.Factory.getInstance().getUsers();
    }

    @Override
    public User getUser(String u_name) {
        return UserDao.Factory.getInstance().getUser(u_name);
    }

    @Override
    public int deleteUser(long id){
        return UserDao.Factory.getInstance().deleteUser(id);
    }

    @Override
    public int insertUser(String u_name, String u_password) {
        return UserDao.Factory.getInstance().insertUser(u_name, u_password);
    }

    @Override
    public boolean userAuthorized(String u_name, String u_password){
        return UserDao.Factory.getInstance().userAuthorized(u_name, u_password);
    }

    @Override
    public User registerUser(User user) {
        return UserDao.Factory.getInstance().registerUser(user);
    }
}
