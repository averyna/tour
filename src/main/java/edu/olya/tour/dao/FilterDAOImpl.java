package edu.olya.tour.dao;

import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import edu.olya.tour.utils.database.ConnectionHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object class implements CountryDao interface
 */
public class FilterDAOImpl implements FilterDAO {

    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConnectionHolder.getConnection()
                    .prepareStatement("SELECT id, country FROM countries;");
            rs = ps.executeQuery();
            while (rs.next()) {
                countries.add(new Country(rs.getInt(1), rs.getString(2)));
            }
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
        return countries;
    }




    public List<TourType> getAllTourTypes(){
        return new ArrayList<>();
    }

    public List<MealType> getAllMealTypes(){
        return new ArrayList<>();
    }

    public List<Hotel> getAllHotels(){
        return new ArrayList<>();
    }
}





