package edu.olya.tour.service;

import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import edu.olya.tour.utils.cache.CacheManager;
import edu.olya.tour.utils.database.TransactionManager;

import java.util.List;

public interface FilterService {

    List<Country> getAllCountries();

    List<TourType> getAllTourTypes();

    List<MealType> getAllMealTypes();

    List<Hotel> getAllHotels();

    public static class Factory {
        private static FilterService instance;

        public static FilterService getInstance() {
            if (instance == null) {
                instance = CacheManager.wrap(
                        TransactionManager.wrap(
                                new FilterServiceImpl()
                        )
                );
            }
            return instance;
        }
    }
}
