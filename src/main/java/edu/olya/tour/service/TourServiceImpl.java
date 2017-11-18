package edu.olya.tour.service;

import edu.olya.tour.dao.TourDAO;
import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;

import java.util.List;
import java.util.Map;

public class TourServiceImpl implements TourService {
    @Override
    public List<TourView> searchTours(Map<String, Object> searchParameters) {
        return TourDAO.Factory.getInstance().searchTours(searchParameters);
    }

    @Override
    public void insertTour(Tour tour) {
        TourDAO.Factory.getInstance().insertTour(tour);
    }

    @Override
    public long deleteTour(long tourId) {
        return TourDAO.Factory.getInstance().deleteTour(tourId);
    }
}
