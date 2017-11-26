package edu.olya.tour.service;

import edu.olya.tour.model.Comment;
import edu.olya.tour.utils.database.TransactionManager;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    int insertComment(Comment comment);

    int deleteComment(long id);

    public static class Factory {
        private static CommentService instance;

        public static CommentService getInstance() {
            if (instance == null) {
                instance = TransactionManager.wrap(new CommentServiceImpl());
            }
            return instance;
        }
    }
}
