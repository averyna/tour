package edu.olya.tour.controller;

import edu.olya.tour.model.User;
import edu.olya.tour.service.UserService;
import edu.olya.tour.utils.ObjectBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

public class RegistrationController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserService.Factory.getInstance();
        String name_param = request.getParameter("name");

        if (userService.getUser(name_param) == null) {
            User user = ObjectBuilder.parse(request.getParameterMap(), new User());
            user = userService.registerUser(user);

            HttpSession session = request.getSession();
            session.setAttribute("role", user.getRole());
            session.setAttribute(User.class.getName(), user);
            request.setAttribute("page", "index.jsp");
        } else {
            request.setAttribute("invalidParam", name_param);
            request.setAttribute("page", "registration.jsp");
        }

        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "registration.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }
}
