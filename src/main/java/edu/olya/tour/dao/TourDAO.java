package edu.olya.tour.dao;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;

import java.util.List;
import java.util.Map;

public interface TourDAO {
    List<TourView> searchTours(Map<String, String[]> searchParameters);
    int insertTour(Tour tour);
    int deleteTour(long tourId);

    public static class Factory {
        private static TourDAO instance = new TourDAOImpl();

        public static TourDAO getInstance() {
            return instance;
        }
    }
}
