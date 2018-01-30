package edu.olya.tour.dao.creators;

import edu.olya.tour.model.TourView;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TourViewRowCreator implements RowCreator<TourView> {

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

}
