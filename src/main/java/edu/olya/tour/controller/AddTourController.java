package edu.olya.tour.controller;

import edu.olya.tour.model.Tour;
import edu.olya.tour.service.FilterService;
import edu.olya.tour.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "AddTourController")
public class AddTourController extends HttpServlet {

    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//в следующей строке в tourService возвращается proxy, приведенный к типу TourServiceImpl
        TourService tourService = TourService.Factory.getInstance();

        //т.к. tourService - это прокси, insertTour будет вызываться не напрямую, а через invoke
        if(!request.getParameterMap().isEmpty()) {
            try {
                Tour tour = Tour.parse(request.getParameterMap());
                tourService.insertTour(tour);
            }catch (NumberFormatException | IllegalAccessException | ParseException e){
                e.printStackTrace();
                request.setAttribute("invalidParams", true);
            }
        }

        populateSearchForm();

        request.setAttribute("page", "add_tour.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    private void populateSearchForm() {
        FilterService filterService = FilterService.Factory.getInstance();
        filterService.getAllCountries();
        filterService.getAllTourTypes();
        filterService.getAllMealTypes();
        filterService.getAllHotels();
    }
}
//todo: change hot_tours.jsp


