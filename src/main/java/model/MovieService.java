package model;

import entity.Member;
import entity.Movie;
import entity.RentMovie;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
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



    public RentMovie getrentMovies(int copymovie_id ,String member_phonenumber ) {
        var session = sessionFactory.openSession();
        RentMovie delmovies;
        if (member_phonenumber== null || member_phonenumber.isEmpty())
            delmovies = (RentMovie) session.createQuery("from RentMovie").list().get(0);
            //delmovies = (RentMovie) session.createQuery("from RentMovie where copymovie_id="+copymovie_id+"and member_phonenumber="+member_phonenumber).getSingleResult();
        else {
            System.out.println("oui");
            delmovies = (RentMovie) session.createQuery("from RentMovie").list().get(0);
            //delmovies = (RentMovie) session.createQuery("from RentMovie where copymovie_id="+copymovie_id+"and member_phonenumber="+member_phonenumber).getSingleResult();}
            System.out.println("delmovies " + delmovies);
        }
        session.close();
        return delmovies;
    }



    public void updaterentMovie(RentMovie delmovie) {
        Objects.requireNonNull(delmovie);
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.update(delmovie);
        System.out.println("delmovies " + delmovie);
        transaction.commit();
        session.close();
    }


}
