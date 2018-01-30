package edu.olya.tour.controller;

import edu.olya.tour.model.Tour;
import edu.olya.tour.service.TourService;
import edu.olya.tour.utils.ObjectBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddTourController")
public class AddTourController extends HttpServlet {

    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TourService tourService = TourService.Factory.getInstance();

        if (!request.getParameterMap().isEmpty()) {
                Tour tour = ObjectBuilder.parse(request.getParameterMap(), new Tour());
                tourService.insertTour(tour);
        }
        request.setAttribute("page", "add_tour.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "add_tour.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }
}


