package edu.olya.tour.controller;

import edu.olya.tour.model.TourView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DelTourController")
public class DelTourController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TourService tourService = TourService.Factory.getInstance();

        //нужно определить, какая кнопка была нажата для перехода ("Удалить" или "Выбрать параметры")
        //или же переход осуществлен по клику вкладки навигационной панели "Удалить тур" (в этом случае button == null)
        String button = request.getParameter("submit_button");
        if (button != null && button.compareTo("Удалить") == 0) {
            long qty = 0;

            String[] tourIds = request.getParameterValues("id");
            for (String id : tourIds) {
                long tourId = Long.parseLong(id);
                qty += tourService.deleteTour(tourId);
            }
            request.setAttribute("deleted", qty);
        } else {

            Map<String, Object> searchParameters = new HashMap<>();

            for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
                searchParameters.put(param.getKey(), param.getValue());
            }

            List<TourView> tours = tourService.searchTours(searchParameters);
            request.setAttribute("tours", tours);
        }

        populateSearchForm(request);
        request.setAttribute("page", "del_tour.jsp");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}


//    TourService tourService = TourService.Factory.getInstance();
//
//    Map<String, Object> searchParameters = new HashMap<>();
//
//        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
//        searchParameters.put(param.getKey(), param.getValue());
//        }
//
//        //т.к. tourService - это прокси, searchTours будет вызываться не на прямую, а через invoke
//        List<TourView> tours = tourService.searchTours(searchParameters);
//
//        populateSearchForm(request);
//
//        request.setAttribute("tours", tours);
//        request.setAttribute("page", "del_tour.jsp");
//        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);