package view;

import controller.HibernateUtils;
import model.MovieService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SearchModifyMovie {
    private JPanel all;
    private JPanel search;
    private JPanel result;
    private JButton searchButton;
    private JTextField titleOfMovie;
    private JPanel result2;

    private final MovieService movieService;

    public SearchModifyMovie(MovieService movieService) {
        this.movieService = Objects.requireNonNull(movieService);
        printMovie(null);

        searchButton.addActionListener(actionEvent -> printMovie(titleOfMovie.getText()));
    }
    private void printMovie(String title) {
        var movies = movieService.getMovies(title);
        result2.removeAll();
        var grid = new GridLayout(movies.size(), 2);
        result2.setLayout(grid);
        for (var movie : movies) {
            var titleLabel = new JLabel(movie.getTitle());
            titleLabel.setOpaque(true);
            var buttonModifyMovie = new JButton("Modifier");
            buttonModifyMovie.setOpaque(true);
            result2.add(titleLabel);
            result2.add(buttonModifyMovie);
        }
        var jsp = new JScrollPane(result2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        result.add(jsp);
        result2.revalidate();
        result.revalidate();
    }
    public static void main(String[] args) {
        var test = new SearchModifyMovie(new MovieService(HibernateUtils.getSessionFactory()));
        var home = new JFrame("videotron");
        home.getContentPane().add(test.all);
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}
