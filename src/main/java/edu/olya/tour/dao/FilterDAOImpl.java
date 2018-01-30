package edu.olya.tour.dao;

import edu.olya.tour.dao.creators.RowCreator;
import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import edu.olya.tour.utils.database.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object class implements FilterDao interface
 */
public class FilterDAOImpl extends AbstractDAO implements FilterDAO {
    @Override
    public List<Country> getAllCountries() {
        return executeQuery("SELECT id, country FROM countries", new RowCreator<Country>(){
            public  Country buildRow(ResultSet rs)throws SQLException{
                return new Country(rs.getInt("id"), rs.getString("country"));
            }
        });
    }

    @Override
    public List<TourType> getAllTourTypes(){
        return executeQuery("SELECT id, tour_type FROM tour_types", new RowCreator<TourType>(){
            public  TourType buildRow(ResultSet rs)throws SQLException{
                return new TourType(rs.getInt("id"), rs.getString("tour_type"));
            }
        });
    }

    @Override
    public List<MealType> getAllMealTypes(){
        return executeQuery("SELECT id, meal_type FROM meal_types", new RowCreator<MealType>(){
            public  MealType buildRow(ResultSet rs)throws SQLException{
                return new MealType(rs.getInt("id"), rs.getString("meal_type"));
            }
        });
    }

    @Override
    public List<Hotel> getAllHotels(){
        return executeQuery("SELECT id, hotel FROM hotels", new RowCreator<Hotel>(){
            public  Hotel buildRow(ResultSet rs)throws SQLException{
                return new Hotel(rs.getInt("id"), rs.getString("hotel"));
            }
        });
    }
}





