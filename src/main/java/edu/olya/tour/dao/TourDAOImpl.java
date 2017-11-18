package edu.olya.tour.dao;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import edu.olya.tour.utils.database.ConnectionHolder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object class implements TourDao interface
 */
public class TourDAOImpl implements TourDAO {

    @Override
    public void insertTour(Tour tour) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConnectionHolder
                    .getConnection()
                    .prepareStatement(
                            "INSERT INTO all_tours (" +
                                    "country_id, tour_type_id, start_date, " +
                                    "adults, children, nights," +
                                    "hotel_id, meal_type_id, price" +
                                    ") VALUES (?,?,?, ?,?,?, ?,?,?) returning id" // аа вставке сразу получаем айиишник из базы который БД сгенерирует
                    );
            ps.setInt(1, tour.getCountryId());
            ps.setInt(2, tour.getTourTypeId());
            ps.setDate(3, new java.sql.Date(tour.getStartDate().getTime()));
            ps.setInt(4, tour.getAdults());
            ps.setInt(5, tour.getChildren());
            ps.setInt(6, tour.getNights());
            ps.setInt(7, tour.getHotelId());
            ps.setInt(8, tour.getMealTypeId());
            ps.setBigDecimal(9, tour.getPrice());

            rs = ps.executeQuery();
            rs.next();
            int tourId = rs.getInt("id");
            tour.setId(tourId);
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
        }

    }

    @Override
    public long deleteTour(long tourId) {
        long qty = 0;
        PreparedStatement ps = null;
        try {
            ps = ConnectionHolder.getConnection().prepareStatement("DELETE FROM all_tours where id = ?;");
            ps.setLong(1, tourId);
            qty = ps.executeUpdate();
            return qty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<TourView> searchTours(Map<String, Object> searchParameters) {
        List<TourView> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * from tour_view ");

        List<Object> params = new ArrayList<>();


        buildSearchSql(sql, params, searchParameters);


        try {
            PreparedStatement ps = ConnectionHolder.getConnection().
                    prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                ps.setObject(i + 1, param);
                System.out.println(i + " " + param);
            }

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                result.add(
                        createTour(resultSet)
                );
            }
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            throw   new RuntimeException("TourDAO searchTours sql exception", e);
        }
        return result;
    }

    private void buildSearchSql(StringBuilder sql, List<Object> params, Map<String, Object> searchParameters) {
        if (searchParameters.isEmpty()) {
            return;
        }

        SearchParam searchParam = SearchParam.parse(searchParameters);
        sql.append("WHERE ");
        if (searchParam.getCountry() != null) {
            sql.append("country = ? ");
            params.add(searchParam.getCountry());
        }
        if (searchParam.getTour_type() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("tour_type = ? ");
            params.add(searchParam.getTour_type());
        }
        if (searchParam.getStart_date() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("start_date >= ? ");
            params.add(new java.sql.Date(searchParam.getStart_date().getTime()));
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
        if (searchParam.getMeal_type() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("meal_type = ? ");
            params.add(searchParam.getMeal_type());
        }
        if (searchParam.getHotel() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("hotel = ? ");
            params.add(searchParam.getHotel());
        }
        if (searchParam.getPrice_from() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("price >= ? ");
            params.add(searchParam.getPrice_from());
        }
        if (searchParam.getPrice_to() != null) {
            if(!params.isEmpty()) {
                sql.append("AND ");
            }
            sql.append("price <= ? ");
            params.add(searchParam.getPrice_to());
        }
    }

    private static TourView createTour(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String country = resultSet.getString("country");
        String tour_type = resultSet.getString("tour_type");
        Date start_date = resultSet.getDate("start_date");
        int adults = resultSet.getInt("adults");
        int children = resultSet.getInt("children");
        int nights = resultSet.getInt("nights");
        String hotel = resultSet.getString("hotel");
        String meal_type = resultSet.getString("meal_type");
        BigDecimal price = resultSet.getBigDecimal("price");

        return new TourView(id, country, tour_type, start_date, adults, children, nights, hotel, meal_type, price);
    }
}

//todo: выводить предупреждение, если цена ОТ больше цены ДО

