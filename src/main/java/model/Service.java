package model;

import entity.CopyMovie;
import entity.Movie;
import entity.MyTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.print.attribute.standard.Copies;
import java.util.List;
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

    public void add(MyTable entity) {
        openSession();
        session.save(entity);
        closeSession();
    }

    public void update(Object entity) {
        openSession();
        session.update(entity);
        closeSession();
    }

    public List<? extends Entity> request(String query) {
        openSession();
        var list = session.createQuery(query).list();
        closeSession();
        return  list;
    }

    public void save(Object object) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }

    public List<CopyMovie> getCopiesIdOfMovie(Movie movie) {
        var session = sessionFactory.openSession();
        List<CopyMovie> copyMovie;
        copyMovie = session.createQuery("from CopyMovie where movie_id = '"  + movie.getTitle() + "'").list();
        session.close();
        return copyMovie;
    }
}
