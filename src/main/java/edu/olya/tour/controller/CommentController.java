package edu.olya.tour.controller;

import edu.olya.tour.model.Comment;
import edu.olya.tour.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CommentController extends HttpServlet {

    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CommentService commentService = CommentService.Factory.getInstance();

        if(!request.getParameterMap().isEmpty()) {
            try {
                Comment comment = Comment.parse(request.getParameterMap());
                commentService.insertComment(comment);
            }catch (NumberFormatException | IllegalAccessException | ParseException e){
                e.printStackTrace();
                //request.setAttribute("invalidParams", true);
            }
        }

        List<Comment> comments = commentService.getAllComments();
        
        request.setAttribute("comments", comments);
        request.setAttribute("page", "comments.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


