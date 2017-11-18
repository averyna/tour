package edu.olya.tour.controller;

import edu.olya.tour.model.User;
import edu.olya.tour.dao.UserDao;
import edu.olya.tour.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@javax.servlet.annotation.WebServlet(name = "UserEnterController", urlPatterns = "/user_enter")
public class UserEnterController extends javax.servlet.http.HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name_param = request.getParameter("name");
        String password_param = request.getParameter("password");
        User u = new User();
        UserDao ud = new UserDaoImpl();
        try {
            if (ud.verifiedUser(name_param, password_param)) {
                session.setAttribute("user_verified", true);
                session.setAttribute("user_verified_name", name_param);
               // request.getHeader("Referer");
                request.setAttribute("page", "index.jsp");
                request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
            }
            else {
                session.setAttribute("user_verified", false);
                request.getRequestDispatcher("/static/jsp/user_enter.jsp").forward(request, response);
//                request.setAttribute("page", "user_enter.jsp");
//                request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
            }
        }catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
