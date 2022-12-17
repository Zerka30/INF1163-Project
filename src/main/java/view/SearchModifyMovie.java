package view;

import model.MovieService;
import model.Service;
import org.hibernate.SessionFactory;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SearchModifyMovie {
    private JPanel all;
    private JPanel result;
    private JButton searchButton;
    private JTextField titleOfMovie;
    private JPanel result2;

    private final MovieService movieService;
    private final SessionFactory sessionFactory;

    public SearchModifyMovie(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory);
        this.movieService = new MovieService(sessionFactory);
        this.sessionFactory = sessionFactory;
        printMovie(null);
        searchButton.addActionListener(actionEvent -> printMovie(titleOfMovie.getText()));
    }

    private void printMovie(String title) {
        var movies = movieService.getMovies(title);
        result.removeAll();
        result2.removeAll();
        var grid = new GridLayout(movies.size(), 2);
        result2.setLayout(grid);
        for (var movie : movies) {
            var titleLabel = new JLabel(movie.getTitle());
            titleLabel.setOpaque(true);
            titleLabel.setSize(new Dimension(100, 20));

            var buttonModifyMovie = new JButton("Modifier");
            buttonModifyMovie.setOpaque(true);
            buttonModifyMovie.setSize(new Dimension(100, 20));

            buttonModifyMovie.addActionListener(actionEvent -> new ModifyAMovie(sessionFactory, movie));
            result2.add(titleLabel);
            result2.add(buttonModifyMovie);
        }
        var jsp = new JScrollPane(result2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        result.add(jsp);
        result2.revalidate();
        result.revalidate();
    }

    public JPanel getPanelWindow() {
        return all;
    }

}
