package edu.olya.tour.controller;

import edu.olya.tour.model.Tour;
import edu.olya.tour.service.TourService;
import edu.olya.tour.utils.database.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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

        populateSearchForm(request);

        request.setAttribute("page", "add_tour.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    private void populateSearchForm(HttpServletRequest request) {
        getValuesFromDB(request, "country", "SELECT DISTINCT id, country FROM countries;");
        getValuesFromDB(request, "tour_type", "SELECT DISTINCT id, tour_type FROM tour_types;");
        getValuesFromDB(request, "meal_type", "SELECT DISTINCT id, meal_type FROM meal_types;");
        getValuesFromDB(request, "hotel", "SELECT DISTINCT id, hotel FROM hotels;");
    }

    private void getValuesFromDB(HttpServletRequest request, String attributeName, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Map<Integer, String> result = new HashMap<>();
        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                result.put(rs.getInt(1), rs.getString(2));
            }
            request.setAttribute(attributeName, result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {

                ConnectionManager.closeConnection(connection);// connection.close();

            }
        }
    }
}
//todo: change hot_tours.jsp


