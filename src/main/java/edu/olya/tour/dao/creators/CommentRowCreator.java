package edu.olya.tour.dao.creators;

import edu.olya.tour.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CommentRowCreator implements RowCreator<Comment> {

    @Override
    public Comment buildRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String author = rs.getString("author");
        Date date = rs.getDate("date");
        String comment = rs.getString("comment");
        return new Comment(id, author, date, comment);
    }
}

