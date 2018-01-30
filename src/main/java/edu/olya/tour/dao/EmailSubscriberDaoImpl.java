package edu.olya.tour.dao;

import edu.olya.tour.dao.creators.EmailSubscriberRowCreator;
import edu.olya.tour.model.EmailSubscriber;
import edu.olya.tour.utils.database.AbstractDAO;

import java.util.ArrayList;
import java.util.List;

public class EmailSubscriberDaoImpl extends AbstractDAO implements EmailSubscriberDao {
    @Override
    public List<EmailSubscriber> getSubscribers() {
        return executeQuery(
                "SELECT name, email from subscribers;",
                new EmailSubscriberRowCreator());
    }

    @Override
    public int deleteSubscriber(EmailSubscriber subscriber) {
        List<Object> params = new ArrayList<>(1);
        params.add(subscriber.getEmail());
        return executeUpdate("DELETE from subscribers WHERE subscribers.email = ?;", params);
    }

    @Override
    public int insertSubscriber(EmailSubscriber subscriber) {
        List<Object> params = new ArrayList<>();
        params.add(subscriber.getName());
        params.add(subscriber.getEmail());
        return executeUpdate("INSERT INTO subscribers (name, email) VALUES (?, ?);", params);
    }
}
