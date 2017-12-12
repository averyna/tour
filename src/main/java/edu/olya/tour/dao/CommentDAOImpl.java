package edu.olya.tour.dao;

import edu.olya.tour.model.Comment;
import edu.olya.tour.utils.database.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAOImpl extends AbstractDAO implements CommentDAO {
    @Override
    public List<Comment> getAllComments() {
        return executeQuery(
                "SELECT id, author, date, comment from comments ORDER BY date;",
                new RowCreator<Comment>() {//todo вынести во вложенный класс
                    @Override
                    public Comment buildRow(ResultSet rs) throws SQLException {
                        long id = rs.getLong(1);
                        String author = rs.getString(2);
                        Date date = rs.getDate(3);
                        String comment = rs.getString(4);
                        return new Comment(id, author, date, comment);
                    }
                }
        );
    }

    @Override
    public int insertComment(Comment comment) {
        List<Object> params = new ArrayList<>();
        params.add(comment.getAuthor());
        params.add(new java.sql.Date(comment.getDate().getTime()));
        params.add(comment.getComment());
        return executeUpdate("INSERT  INTO comments (author, date, comment) VALUES (?, ?, ?);", params);
    }
    @Override
    public int deleteComment(long id) {
        List<Object> params = new ArrayList<>(1);
        params.add(id);
        return executeUpdate("DELETE FROM comments where id = ?;", params);
    }
}
