package edu.olya.tour.controller;

import edu.olya.tour.model.User;
import edu.olya.tour.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserService.Factory.getInstance();
        String name_param = request.getParameter("name");
        String password_param = request.getParameter("password");

        //проверяем, занято ли такое имя (есть ли в базе пользователь с таким именем)
        if (userService.getUser(name_param) == null) {
            //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
            //тут архитектурная ошибка - ввязанный код который отвечает за лоигику находится в классе который должен заниматься
            // только преобраоованием параметров запроса в объект и передачу этого объекта на уровень Серииса (там дде бизнес логика описывается)
            userService.insertUser(name_param, password_param);
            User user = userService.getUser(name_param);
            // ддин из правильных вариантов
//            User user = User.build(request.getParameterMap());
//            user = userService.registerUser(user); // метод возвращает тот же самый объект только с назначенным айдишником из БД
            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
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
