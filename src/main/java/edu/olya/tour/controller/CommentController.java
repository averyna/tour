package edu.olya.tour.controller;

import com.google.gson.Gson;
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

    private CommentService commentService = CommentService.Factory.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardComments(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Comment comment = Comment.parse(request.getParameterMap());
            commentService.insertComment(comment);
        } catch (NumberFormatException | IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }

        forwardComments(request, response);
    }

    /*теперь основной вопрос котррый тебя будет мучать как отправить HTTP DELETE :) */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("id: " + req.getHeader("id"));
        commentService.deleteComment(Long.parseLong(req.getHeader("id")));//todo подобные методы разнеси на отдельные строки, если свалится ошибка будет непонятно на чем именно она свалилась
        forwardComments(req, resp);

        //запрос на сервер был без перезагрузки страницы
    }

    private void forwardComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comment> comments = commentService.getAllComments();

        request.setAttribute("comments", comments);
        request.setAttribute("page", "comments.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);

    }
}


