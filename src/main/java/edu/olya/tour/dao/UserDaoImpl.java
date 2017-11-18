package edu.olya.tour.dao;

import edu.olya.tour.model.User;
import edu.olya.tour.utils.database.ConnectionHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object class implements UserDao interface
 */
public class UserDaoImpl implements UserDao {// Ctrl + Q - llok to the javadocs

    /**
     * Method retuns all users
     * @return users list
     * @throws ClassNotFoundException occurs when class not found
     * @throws SQLException etc
     */
    public List<User> getUsers() throws ClassNotFoundException, SQLException{
        PreparedStatement ps = ConnectionHolder.getConnection().prepareStatement("SELECT id, name, password from users;");
        ResultSet resultSet = ps.executeQuery();

        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);

            users.add(new User(id, name, password));
        }
        ps.close();
        resultSet.close();
        return users;
    }

    /**
     *Method search user by user name
     * @param u_name user name
     * @return returns user object
     * @throws ClassNotFoundException xxx
     * @throws SQLException yyy
     */
    public User getUser(String u_name) throws ClassNotFoundException, SQLException{
        User user = null;
        PreparedStatement ps = ConnectionHolder.getConnection().prepareStatement("SELECT id, name, password from users WHERE users.name = ?;");
        ps.setString(1, u_name);
        ResultSet resultSet = ps.executeQuery();

        if(resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            user = new User(id, name, password);
        }
        ps.close();
        resultSet.close();
        return user;
    }

    /**
     * <b>returns true <i>if <u>the</u></i> User</b> with specified name and email exists in database, otherwise returns false
     */
    public boolean existsUser(String u_name)throws ClassNotFoundException, SQLException{
        PreparedStatement ps = ConnectionHolder.getConnection().prepareStatement("SELECT id, name from users WHERE users.name = ? ;");
        ps.setString(1, u_name);
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            ps.close();
            resultSet.close();
            return true;
        }
        ps.close();
        resultSet.close();
        return false;
    }

    public  void deleteUser(String u_name) throws ClassNotFoundException, SQLException{
        PreparedStatement ps = ConnectionHolder.getConnection().prepareStatement("DELETE from users WHERE users.name = ? ;");
        ps.setString(1, u_name);
        ps.executeUpdate();
        ps.close();
    }

    public  void addUser(String u_name, String u_password) throws ClassNotFoundException, SQLException{
        PreparedStatement ps = ConnectionHolder.getConnection().prepareStatement("INSERT  INTO users (name, password) VALUES (?, ?);");
        ps.setString(1, u_name);
        ps.setString(2, u_password);
        ps.executeUpdate();
        ps.close();
    }

    public boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException{
        PreparedStatement ps = ConnectionHolder.getConnection().prepareStatement("SELECT id, name from users WHERE users.name = ? AND users.password = ?;");
        ps.setString(1, u_name);
        ps.setString(2, u_password);
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            ps.close();
            resultSet.close();
            return true;
        }
        ps.close();
        resultSet.close();
        return false;
    }
}


