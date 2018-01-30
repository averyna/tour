package edu.olya.tour.service;

import edu.olya.tour.dao.EmailSubscriberDao;
import edu.olya.tour.model.EmailSubscriber;

import java.util.List;

public class EmailSubscriberServiceImpl implements EmailSubscriberService {
    @Override
    public List<EmailSubscriber> getSubscribers() {
        return EmailSubscriberDao.Factory.getInstance().getSubscribers();
    }

    @Override
    public int deleteSubscriber(EmailSubscriber subscriber) {
        return EmailSubscriberDao.Factory.getInstance().deleteSubscriber(subscriber);
    }

    @Override
    public int insertSubscriber(EmailSubscriber subscriber) {
        return EmailSubscriberDao.Factory.getInstance().insertSubscriber(subscriber);
    }
}
