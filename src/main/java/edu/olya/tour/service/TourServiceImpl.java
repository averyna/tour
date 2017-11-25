package edu.olya.tour.service;

import edu.olya.tour.dao.TourDAO;
import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import edu.olya.tour.utils.cache.CacheConfig;
import edu.olya.tour.utils.cache.CacheParam;

import java.util.List;
import java.util.Map;

public class TourServiceImpl implements TourService {
    @Override
    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    public List<TourView> searchTours(Map<String, String[]> searchParameters) {
        return TourDAO.Factory.getInstance().searchTours(searchParameters);
    }

    @Override
    public int insertTour(Tour tour) {
        return TourDAO.Factory.getInstance().insertTour(tour);
    }

    @Override
    public int deleteTour(long tourId) {
        return TourDAO.Factory.getInstance().deleteTour(tourId);
    }
}
