package edu.olya.tour.dao;

import edu.olya.tour.dao.creators.TourViewRowCreator;
import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import edu.olya.tour.utils.ObjectBuilder;
import edu.olya.tour.utils.database.AbstractDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TourDAOImpl extends AbstractDAO implements TourDAO {

    @Override
    public int insertTour(Tour tour) {
        List<Object> params = new ArrayList<>();
        params.add(tour.getCountryId());
        params.add(tour.getTourTypeId());
        params.add(new java.sql.Date(tour.getStartDate().getTime()));
        params.add(tour.getAdults());
        params.add(tour.getChildren());
        params.add(tour.getNights());
        params.add(tour.getHotelId());
        params.add(tour.getMealTypeId());
        params.add(tour.getPrice());
        return executeUpdate("INSERT INTO all_tours (" +
                        "country_id, tour_type_id, start_date, " +
                        "adults, children, nights, " +
                        "hotel_id, meal_type_id, price) " +
                        "VALUES (?,?,?, ?,?,?, ?,?,?);",
                params);
    }

    @Override
    public int deleteTour(long tourId) {
        List<Object> params = new ArrayList<>(1);
        params.add(tourId);
        return executeUpdate("DELETE FROM all_tours where id = ?;", params);
    }


    @Override
    public List<TourView> searchTours(Map<String, String[]> searchParameters) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * from tour_view ");
        buildSearchSql(sql, params, searchParameters);

        return executeQuery(sql.toString(),
                new TourViewRowCreator(), params);
    }

    private void buildSearchSql(StringBuilder sql, List<Object> params, Map<String, String[]> searchParameters) {
        if (searchParameters == null || searchParameters.isEmpty()) {
            return;
        }

        TourView searchParam = ObjectBuilder.parse(searchParameters, new TourView());
        sql.append("WHERE ");
        if (searchParam.getCountry() != null) {
            sql.append("country = ? ");
            params.add(searchParam.getCountry());
        }
        if (searchParam.getTourType() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("tour_type = ? ");
            params.add(searchParam.getTourType());
        }
        if (searchParam.getStartDate() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("start_date >= ? ");
            params.add(new java.sql.Date(searchParam.getStartDate().getTime()));
        }
        if (searchParam.getAdults() != 0) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("adults = ? ");
            params.add(searchParam.getAdults());
        }
        if (searchParam.getChildren() != 0) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("children = ? ");
            params.add(searchParam.getChildren());
        }
        if (searchParam.getNights() != 0) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("nights = ? ");
            params.add(searchParam.getNights());
        }
        if (searchParam.getMealType() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("meal_type = ? ");
            params.add(searchParam.getMealType());
        }
        if (searchParam.getHotel() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("hotel = ? ");
            params.add(searchParam.getHotel());
        }
        if (searchParam.getPriceFrom() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("price >= ? ");
            params.add(searchParam.getPriceFrom());
        }
        if (searchParam.getPriceTo() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("price <= ? ");
            params.add(searchParam.getPriceTo());
        }
    }
}


