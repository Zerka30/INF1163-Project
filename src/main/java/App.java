import entity.Movie;
import model.MovieService;
import view.SearchMovieToModify;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        var sessionFactory = HibernateUtils.getSessionFactory();
        var searchMovie = new SearchMovieToModify(sessionFactory);
        createFrame(searchMovie.getWindow());

        MovieService ms = new MovieService(sessionFactory);
        //Movie matrix = new Movie("matrix",true);
        ms.createMovie("matrix",true);

    }

    public static void createFrame(Component e) {
        var home = new JFrame("videotron");
        home.getContentPane().add(e);
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }


}
