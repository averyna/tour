package edu.olya.tour.controller;


import edu.olya.tour.model.TourView;
import edu.olya.tour.service.TourService;
import edu.olya.tour.utils.database.ConnectionManager;

import javax.servlet.ServletException;
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

public class TourSearchController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //в следующей строке в tourService возвращается proxy, приведенный к типу TourServiceImpl
        TourService tourService = TourService.Factory.getInstance();

        Map<String, Object> searchParameters = new HashMap<>();

        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            searchParameters.put(param.getKey(), param.getValue());
        }

        //т.к. tourService - это прокси, searchTours будет вызываться не на прямую, а через invoke
        List<TourView> tours = tourService.searchTours(searchParameters);

        populateSearchForm(request);

        request.setAttribute("tours", tours);
        request.setAttribute("page", "tour_search.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    private void populateSearchForm(HttpServletRequest request) {
        getValuesFromDB(request, "country", "SELECT DISTINCT id, country FROM countries;");
        getValuesFromDB(request, "tour_type", "SELECT DISTINCT id, tour_type FROM tour_types;");
        getValuesFromDB(request, "meal_type", "SELECT DISTINCT id, meal_type FROM meal_types;");
        //todo: there is no input field in tour_search.jsp to choose hotel
        //getValuesFromDB(request, "hotel", "SELECT DISTINCT id, hotel FROM hotels;");
    }

    private void getValuesFromDB(HttpServletRequest request, String attributeName, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Map<Integer, String> result = new HashMap<>();
        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();// где закрытие соединения и почему ты из контроллера напрямую лезеьь в БД
            // у тебя нарушение архитектуры привело к утечке ресурсов
            // т.е. ты обошла Transaction Manager и решила не закрывать соединения
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
