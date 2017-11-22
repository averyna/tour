package edu.olya.tour.service;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import edu.olya.tour.utils.cache.CacheManager;
import edu.olya.tour.utils.database.TransactionManager;

import java.util.List;
import java.util.Map;

public interface TourService {
    //@CacheConfig()
    List<TourView> searchTours(Map<String, Object> searchParameters);

    void insertTour(Tour tour);

    long deleteTour(long tourId);

    public static class Factory {
        private static TourService instance;

        public static TourService getInstance() {
            if (instance == null) {
                instance = CacheManager.wrap(
                        TransactionManager.wrap(
                                new TourServiceImpl()
                        )
                ); //возвращается proxy, приведенный к типу TourServiceImpl
            }
            return instance;
        }
    }
}
