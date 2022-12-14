import entity.Movie;
import model.MovieService;
import view.CreateMovie;
import view.SearchMovieToModify;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        var sessionFactory = HibernateUtils.getSessionFactory();
        MovieService ms = new MovieService(sessionFactory);
        CreateMovie createMovie = new CreateMovie(ms,sessionFactory);
        createFrame(createMovie.getWindow());

        Movie movietest = new Movie("ManualTry",1,"DVD","Action",true);
        ms.createMovie(movietest);

/*
        ms.modifyMovie(matrix,false);
        System.out.println("Movie updated");*/

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
