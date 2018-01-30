package edu.olya.tour.dao.creators;

import edu.olya.tour.model.EmailSubscriber;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailSubscriberRowCreator implements RowCreator<EmailSubscriber> {

    @Override
    public EmailSubscriber buildRow(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String email = rs.getString("email");
        return new EmailSubscriber(name, email);
    }
}

