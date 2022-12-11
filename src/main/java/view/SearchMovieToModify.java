package view;

import entity.Movie;
import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class SearchMovieToModify {

    /**
     * This class can to get movies of database
     */
    private final MovieService movieService;

    /**
     *
     */
    private List<Movie> movies;
    private JPanel window;
    private JTable tabMovies;
    private JTextField title;
    private JButton search;

    public SearchMovieToModify(SessionFactory sessionFactory) {
        //window.updateUI();
        this.movieService = new MovieService(Objects.requireNonNull(sessionFactory));
        var columns = new String[]{"title", "modifier"};
        movies = movieService.getMovies(null);

        Object[][] data = new Object[movies.size()][2];

        for (var i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            data[i][0] = movie.getTitle();
            var button = new JButton("Modifier");
            button.addActionListener(actionEvent -> {
                // ouvre la fenêtre de modification
            });
            data[i][1] = button;

        }

        tabMovies = new JTable(data, columns);
    }

    public JPanel getWindow() {
        return window;
    }

}
