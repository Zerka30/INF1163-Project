package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import entity.Movie;
import model.Service;
import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class BackMovie extends JFrame {
    private JPanel Retourfilmpanel;

    private JComboBox<Object> movieJComboBox;

    private JComboBox<Object> supportJComboBox;
    private JButton buttontest;
    private JLabel labeltest2;
    private JTextField phone;
    private JTextField Moviename;
    private JPanel result;
    private JButton searchMovie;
    private JPanel result2;
    private final MovieService movieService;
    private final Service service;


    public BackMovie(SessionFactory sessionFactory) {

        this.movieService = new MovieService(Objects.requireNonNull(sessionFactory));
        this.service = new Service(Objects.requireNonNull(sessionFactory));
        printMovies();
        printSupport();
        buttontest.addActionListener(e -> {
            //grab the text
            var memberId = phone.getText();
            var movie = movieJComboBox.getSelectedItem().toString();
            var support = supportJComboBox.getSelectedItem().toString();
            var idMovie = movieService.getIdOfCopyMovie(movie, support);
            var rentMovie = movieService.getrentMovies(memberId, idMovie);
            rentMovie.setrentbackdate();
            movieService.updaterentMovie(rentMovie);
        });
    }

    private void printMovies() {
        searchMovie.addActionListener(actionEvent -> {
            result.removeAll();
            var movies = service.getMovies(Moviename.getText());
            var moviesTitle = movies.stream().map(Movie::getTitle).toArray();
            movieJComboBox = new JComboBox<>(moviesTitle);
            result.add(movieJComboBox);
            result.revalidate();
        });
    }

    private void printSupport() {
        var type = new String[]{"dvd", "blueray"};
        supportJComboBox = new JComboBox<>(type);
        result2.add(supportJComboBox);
        result2.revalidate();
    }

    public JPanel getWindow() {
        return Retourfilmpanel;
    }

}
