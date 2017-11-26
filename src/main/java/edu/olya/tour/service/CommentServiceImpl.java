package edu.olya.tour.service;

import edu.olya.tour.dao.CommentDAO;
import edu.olya.tour.model.Comment;

import java.util.List;

public class CommentServiceImpl implements CommentService{
    @Override
    public List<Comment> getAllComments() {
        return CommentDAO.Factory.getInstance().getAllComments();
    }

    @Override
    public int insertComment(Comment comment) {
        return CommentDAO.Factory.getInstance().insertComment(comment);
    }

    @Override
    public int deleteComment(long id) {
        return CommentDAO.Factory.getInstance().deleteComment(id);
    }
}
