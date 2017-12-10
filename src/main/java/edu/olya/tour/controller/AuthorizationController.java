package edu.olya.tour.controller;

import edu.olya.tour.model.User;
import edu.olya.tour.dao.UserDao;
import edu.olya.tour.dao.UserDaoImpl;
import edu.olya.tour.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AuthorizationController extends javax.servlet.http.HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name_param = request.getParameter("name");
        String password_param = request.getParameter("password");

        UserService userService = UserService.Factory.getInstance();
        User user = userService.getUser(name_param);
        if(password_param.equals(user.getPassword())) {
            session.setAttribute("role", user.getRole()); //это делать не обязательно
            session.setAttribute(User.class.getName(), user);
            request.setAttribute("page", "index.jsp");
        }else {
            request.setAttribute("invalidParam", true);
            request.setAttribute("page", "authorization.jsp");
        }
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("page", "authorization.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }
}
