package edu.olya.tour.controller;

import edu.olya.tour.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrationController", urlPatterns = "/register")
public class RegistrationController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name_param = request.getParameter("name");

        UserService userService = UserService.Factory.getInstance();//вернется UserServiceImpl

        try{
            if(userService.existsUser(name_param)){
                request.setAttribute("user_exists", true);
                request.getRequestDispatcher("/static/jsp/registration.jsp").forward(request, response);
            }
            else {
                String password_param = request.getParameter("password");

                userService.addUser(name_param, password_param);
                //request.setAttribute("page", "index.jsp");
                //request.getRequestDispatcher("/static/layout.jsp").forward(request, response);
                //session.removeAttribute("user_verified_name");
                session.setAttribute("user_verified", true);
                session.setAttribute("user_verified_name", name_param);
                request.setAttribute("page", "index.jsp");
                request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
            }
        }catch (ClassNotFoundException |SQLException ex){
            ex.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
