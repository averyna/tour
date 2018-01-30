package edu.olya.tour.dao;

import edu.olya.tour.model.EmailSubscriber;

import java.util.List;

public interface EmailSubscriberDao {

    List<EmailSubscriber> getSubscribers();

    int deleteSubscriber(EmailSubscriber subscriber);

    int insertSubscriber(EmailSubscriber subscriber);

    public static class Factory {
        private static EmailSubscriberDao instance = new EmailSubscriberDaoImpl();

        public static EmailSubscriberDao getInstance() {
            return instance;
        }
    }
}
