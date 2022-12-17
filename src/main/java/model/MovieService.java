package model;

import entity.Category;
import entity.Movie;
import entity.RentMovie;
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

    public List<Category> getCategories() {
        var session = sessionFactory.openSession();
        List<Category> categories;
        categories = session.createQuery("from Category").list();
        session.close();
        return categories;
    }

    public RentMovie getrentMovies(String memberId, int copymovieId ) {
        var session = sessionFactory.openSession();
        RentMovie rentMovie;
        rentMovie = (RentMovie) session.createQuery("from RentMovie where member_phonenumber ='" + memberId + "' and copymovie_id =" + copymovieId).uniqueResult();
        session.close();
        return rentMovie;
    }


    public void updaterentMovie(RentMovie delmovie) {
        Objects.requireNonNull(delmovie);
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.update(delmovie);
        transaction.commit();
        session.close();
    }

    public int getIdOfCopyMovie(String title, String support){
        var session = sessionFactory.openSession();
        var copymovie = (int) session.createNativeQuery("select id from CopyMovie where movie_id='" + title + "' and support='"+support+"'").uniqueResult();
        session.close();
        return copymovie;
    }
}


