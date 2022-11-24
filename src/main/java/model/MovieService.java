package model;

import entity.Movie;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class MovieService {
    private final SessionFactory sessionFactory;

    public MovieService(SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    /**
     * Get all the movies of the database in order to the title of the movie
     * @param title : title of the movie
     * @return
     */
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
}