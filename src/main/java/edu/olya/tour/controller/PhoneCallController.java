package edu.olya.tour.controller;

import com.google.gson.Gson;
import edu.olya.tour.model.ClientToCall;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class PhoneCallController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        ClientToCall client = gson.fromJson(request.getHeader("Json"), ClientToCall.class);

        sendToManager(client);
    }

    private void sendToManager(ClientToCall client) {
        final String username = "susanin";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        //session.setDebug(true);

        String receiver = "olga_averyna@mail.ru";

        try {
            Address[] receiverAddress = InternetAddress.parse(receiver);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("susanin@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    receiverAddress);
            message.setSubject("New contact to call");
            message.setText("Client's name: " + client.getName() + "\nPhone: " + client.getPhone());

            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
