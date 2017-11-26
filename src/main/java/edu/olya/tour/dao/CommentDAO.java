package edu.olya.tour.dao;

import edu.olya.tour.model.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> getAllComments();

    int insertComment(Comment comment);

    int deleteComment(long id);

    public static class Factory {
        private static CommentDAO instance = new CommentDAOImpl();

        public static CommentDAO getInstance() {
            return instance;
        }
    }
}
