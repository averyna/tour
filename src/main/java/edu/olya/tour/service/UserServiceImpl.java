package edu.olya.tour.service;

import edu.olya.tour.dao.UserDao;
import edu.olya.tour.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{

    public List<User> getUsers()throws ClassNotFoundException, SQLException{
        return UserDao.Factory.getInstance().getUsers();
    }
    public User getUser(String u_name) throws ClassNotFoundException, SQLException{
        return UserDao.Factory.getInstance().getUser(u_name);
    }
    public void deleteUser(String u_name)throws ClassNotFoundException, SQLException{
        UserDao.Factory.getInstance().deleteUser(u_name);
    }
    public void addUser(String u_name, String u_password)throws ClassNotFoundException, SQLException{
        UserDao.Factory.getInstance().addUser(u_name, u_password);
    }
    public boolean existsUser(String u_name)throws ClassNotFoundException, SQLException{
        return UserDao.Factory.getInstance().existsUser(u_name);
    }
    public boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException{
        return UserDao.Factory.getInstance().verifiedUser(u_name, u_password);
    }
}
