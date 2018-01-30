package edu.olya.tour.controller;

import com.google.gson.Gson;
import edu.olya.tour.model.EmailSubscriber;
import edu.olya.tour.service.EmailSubscriberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MailController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        EmailSubscriber client = gson.fromJson(request.getHeader("Json"), EmailSubscriber.class);

        EmailSubscriberService subscriber = EmailSubscriberService.Factory.getInstance();
        subscriber.insertSubscriber(client);
    }
}
