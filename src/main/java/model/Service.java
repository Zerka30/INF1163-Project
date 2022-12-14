package model;

import entity.*;
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
        List<CopyMovie> copyMovie = session.createQuery("from CopyMovie where movie_id = '"  + movie.getTitle() + "'").list();
        session.close();
        return copyMovie;
    }

    public Member getMember(String phoneNumber) {
        var session = sessionFactory.openSession();
        var m = (Member) session.createQuery("from Member where phonenumber = '" + phoneNumber + "'").getSingleResult();
        session.close();
        return m;
    }
    public List<Movie> getMovies(String title) {
        var session = sessionFactory.openSession();
        List<Movie> movies;
        if (title == null || title.isEmpty())
            movies = session.createQuery("from Movie").list();
        else
            movies = session.createQuery("from Movie where title like '%" + title + "%'").list();
        session.close();
        return movies;
    }

    public Movie getMovie(String title) {
        var session = sessionFactory.openSession();
        var m = (Movie) session.createQuery("from Movie where title= '" + title + "'").getSingleResult();
        session.close();
        return m;
    }

    public CopyMovie getCopyMovie(String title, String support) {
        var session = sessionFactory.openSession();
        var copyMovie = (CopyMovie) session.createQuery("from CopyMovie where movie_id = '" + title + "' and support = '" + support + "'").getSingleResult();
        session.close();
        return copyMovie;
    }

    public List<RentMovie> getRentMovieLate() {
        var session = sessionFactory.openSession();
        List<RentMovie> rentMovies;
        rentMovies = session.createQuery("from RentMovie where rentbackuser is null and rentbackdate < now()").list();
        session.close();
        return rentMovies;
    }

    public String getMovieFromCopyMovie(int id) {
        var session = sessionFactory.openSession();
        var title = (String) session.createNativeQuery("select movie_id from CopyMovie c where c.id =" + id).uniqueResult();
        session.close();
        return title;
    }

    public List<String> getCategoriesFromMovie(Movie movie) {
        var session = sessionFactory.openSession();
        var title = (List<String>) session.createNativeQuery("select name from category_movie where title ='" + movie.getTitle() + "'").list();
        session.close();
        return title;
    }
}
