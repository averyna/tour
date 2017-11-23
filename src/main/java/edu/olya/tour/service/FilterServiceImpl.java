package edu.olya.tour.service;


import edu.olya.tour.dao.FilterDAO;
import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import edu.olya.tour.utils.cache.CacheConfig;
import edu.olya.tour.utils.cache.CacheParam;

import javax.servlet.ServletContext;
import java.util.List;

public class FilterServiceImpl implements FilterService {

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    public List<Country> getAllCountries() {
        return FilterDAO.Factory.getInstance().getAllCountries();
    }

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    public List<TourType> getAllTourTypes() {
        return FilterDAO.Factory.getInstance().getAllTourTypes();
    }

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    public List<MealType> getAllMealTypes() {
        return FilterDAO.Factory.getInstance().getAllMealTypes();
    }

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    public List<Hotel> getAllHotels() {
        return FilterDAO.Factory.getInstance().getAllHotels();
    }

}