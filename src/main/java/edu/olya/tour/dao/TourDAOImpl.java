package edu.olya.tour.dao;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import edu.olya.tour.utils.database.AbstractDAO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object class implements TourDao interface
 */
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
                new RowCreator<TourView>() {
                    @Override
                    public TourView buildRow(ResultSet rs) throws SQLException {
                        int id = rs.getInt("id");
                        String country = rs.getString("country");
                        String tour_type = rs.getString("tour_type");
                        Date start_date = rs.getDate("start_date");
                        int adults = rs.getInt("adults");
                        int children = rs.getInt("children");
                        int nights = rs.getInt("nights");
                        String hotel = rs.getString("hotel");
                        String meal_type = rs.getString("meal_type");
                        BigDecimal price = rs.getBigDecimal("price");

                        return new TourView(id, country, tour_type, start_date,
                                adults, children, nights, hotel, meal_type, price);
                    }
                }, params);
    }


//    private List<TourView> executeQuery (String sql,
//                                         RowCreator<TourView> rowCreator,
//                                         Map<String, String[]> searchParameters){
//        List<TourView> result = new ArrayList<>();
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//
//
//        try {
//            ps = ConnectionHolder.getConnection().
//                    prepareStatement(sb.toString());
//
//            for (int i = 0; i < params.size(); i++) {
//                Object param = params.get(i);
//                ps.setObject(i + 1, param);
//                System.out.println(i + " " + param);
//            }
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                result.add(
//                        rowCreator.buildRow(rs)
//                );
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }



    private void buildSearchSql(StringBuilder sql, List<Object> params, Map<String, String[]> searchParameters) {
        if (searchParameters.isEmpty()) {
            return;
        }

        TourView searchParam = TourView.parse(searchParameters);
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


