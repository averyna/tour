package edu.olya.tour.service;

import edu.olya.tour.model.EmailSubscriber;
import edu.olya.tour.utils.database.TransactionManager;

import java.util.List;

public interface EmailSubscriberService {

    List<EmailSubscriber> getSubscribers();

    int deleteSubscriber(EmailSubscriber subscriber);

    int insertSubscriber(EmailSubscriber subscriber);

    public static class Factory {
        private static EmailSubscriberService instance;

        public static EmailSubscriberService getInstance() {
            if (instance == null) {
                instance = TransactionManager.wrap(new EmailSubscriberServiceImpl());
            }
            return instance;
        }
    }
}
