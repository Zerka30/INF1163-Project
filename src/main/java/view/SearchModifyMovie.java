package view;

import controller.HibernateUtils;
import model.MovieService;
import model.Service;
import org.hibernate.SessionFactory;

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
    private final SessionFactory sessionFactory;

    public SearchModifyMovie(MovieService movieService, SessionFactory sessionFactory) {
        this.movieService = Objects.requireNonNull(movieService);
        this.sessionFactory = sessionFactory;
        printMovie(null);
       // result2.setSize(new Dimension());
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
            buttonModifyMovie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    var panel1 = new JPanel();
                    panel1.setBackground(Color.BLUE);
                    var modifyMovie = new ModifyAMovie(new Service(sessionFactory), movie);
                    panel1.removeAll();
                    panel1 = modifyMovie.getPanelWindow();
                    panel1.revalidate();

                  //  test(sessionFactory);
                }
            });
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
