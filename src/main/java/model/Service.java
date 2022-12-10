package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Objects;

public class Service {
    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    public Service(SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }
    private boolean objectIsEntity() {
        return false;
    }

    private void openSession() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }
    private void closeSession() {
        transaction.commit();
        session.close();
    }

    public void add(Object entity) {
        openSession();
        session.save(entity);
        closeSession();
    }

    public void update(Object entity) {
        openSession();
        session.update(entity);
        closeSession();
    }
}
