package edu.olya.tour.controller;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PhoneCallController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Gson gson = new Gson();
//        ShortUser json = gson.fromJson(...)
        String myJson = request.getHeader("Json");
        System.out.println("myJson:" + myJson);

//todo: тут должна быть отправка письма менеджеру с информацией о клиенте, заказавшем звонок

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
