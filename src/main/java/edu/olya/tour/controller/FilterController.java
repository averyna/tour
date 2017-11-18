package edu.olya.tour.controller;


import edu.olya.tour.model.Country;
import edu.olya.tour.service.FilterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//todo:удалить этот сервлет (нужен для учебного примера)

public class FilterController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Country> countries = FilterService.Factory.getInstance().getAllCountries(request.getServletContext());
    }

}

//   /opt/apache-tomcat-9.0.1/bin/catalina.sh start
//   /opt/apache-tomcat-9.0.1/bin/catalina.sh stop
//   /opt/apache-tomcat-9.0.1/bin/catalina.sh jpda start
