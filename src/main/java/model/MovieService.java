package model;

import entity.Category;
import entity.Movie;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public List<Category> getCategories() {
        var session = sessionFactory.openSession();
        List<Category> categories;
        categories = session.createQuery("from Category").list();
        session.close();
        return categories;
    }

    public void createMovie(String title, Boolean isNew, Set<Category> categories)
    {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        Movie movie = new Movie(title,isNew, categories);
        session.save(movie);
        transaction.commit();
        session.close();
    }
}
