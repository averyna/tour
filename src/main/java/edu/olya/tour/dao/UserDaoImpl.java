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
     *Method search all users
     * @return returns list of users
     */
    @Override
    public List<User> getUsers() {
        return executeQuery(
                "SELECT id, name, password from users;",
                new RowCreator<User>() {
                    @Override
                    public User buildRow(ResultSet rs) throws SQLException {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        String password = rs.getString(3);
                        return new User(id, name, password);
                    }
                }
        );
    }

    /**
     *Method search user by user name
     * @param u_name user name
     * @return returns user object
     */
    public User getUser(String u_name){
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        List<User> result = executeQuery(
                "SELECT id, name, password from users WHERE users.name = ?",
                new RowCreator<User>(){
                    @Override
                    public User buildRow(ResultSet rs) throws SQLException {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        String password = rs.getString(3);
                        return new User(id, name, password);
                    }
                }, params
        );
        if(result.isEmpty()) {
            return null;
        }
        if(result.size() > 1) {
            throw new RuntimeException("Not single result");
        }
        return result.get(0);
    }

//    /**
//     * Method exequtes query for getUser(...) method
//     * @param sql query string
//     * @param rowCreator method creates user object from resultSet
//     * @param u_name user name
//     * @return returns user object
//     */
//    private User executeQuery (String sql, RowCreator<User> rowCreator, String u_name ){
//        User user = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = ConnectionHolder.getConnection()
//                    .prepareStatement(sql);
//            ps.setString(1, u_name);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                user = rowCreator.buildRow(rs);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return user;
//    }

    /**
     * Method determines whether user with specified name exists in database
     * @param u_name user name
     * @return <b>returns true <i>if <u>the</u></i> User</b> with specified name exists in database, otherwise returns false
     */
    public boolean existsUser(String u_name){
            return (getUser(u_name) != null)?  true : false;
    }

    /**
     * Deletes user with specified id parameter
     * @param id user id from database
     */
    public int deleteUser(long id){
        List<Object> params = new ArrayList<>(1);
        params.add(id);
        return executeUpdate("DELETE from users WHERE users.id = ?;", params);
    }

    /**
     * Inserts user
     * @param u_name user name
     * @param u_password user password
     */
    public  int insertUser(String u_name, String u_password){
        List<Object> params = new ArrayList<>();
        params.add(u_name);
        params.add(u_password);
        return executeUpdate("INSERT  INTO users (name, password) VALUES (?, ?);", params);
    }

    //todo: change this method if it is used
    /**
     * <b>returns true <i>if <u>the</u></i> User</b> with specified name and email exists in database, otherwise returns false
     */
    public boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException{
        PreparedStatement ps = ConnectionHolder.getConnection().
                prepareStatement("SELECT id, name from users WHERE users.name = ? AND users.password = ?;");
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


