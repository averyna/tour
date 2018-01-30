package edu.olya.tour.dao.creators;

import edu.olya.tour.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowCreator implements RowCreator<User> {

    @Override
    public User buildRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String password = rs.getString("password");
        String role = rs.getString("role");
        return new User(id, name, password, role);

    }
}

