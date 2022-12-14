package view;

import controller.HibernateUtils;
import entity.Movie;
import model.MovieService;
import model.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class ModifyAMovie {
    private JPanel panel1;
    private JPanel formMovie;
    private JPanel formMovieInclude;
    private JButton modifyMovie;
    private JCheckBox news;
    private JLabel titleLabel;

    private Service service;
    private Movie movie;
    public ModifyAMovie(Service service, Movie movie) {
        this.service = Objects.requireNonNull(service);
        this.movie = Objects.requireNonNull(movie);
        initTabForSupport();
        titleLabel.setText(movie.getTitle());
        news.setSelected(movie.isNews());
        news.revalidate();
    }

    private void initTabForSupport() {
        var copiesMovie = service.getCopiesIdOfMovie(movie);
        var grid2 = new GridLayout(copiesMovie.size() + 1, 2);
        formMovieInclude.setLayout(grid2);
        var supportLabel = new JLabel("Support");
        var quantityLabel = new JLabel("Quantité");
        formMovieInclude.add(supportLabel);
        formMovieInclude.add(quantityLabel);
        var field = new ArrayList<JTextField>();

        for (var copyMovie : copiesMovie) {
            var supportElement = new JLabel(copyMovie.getSupport());
            var quantityElement = new JTextField(String.valueOf(copyMovie.getQuantity()));
            field.add(quantityElement);
            formMovieInclude.add(supportElement);
            formMovieInclude.add(quantityElement);
        }

        var jsp2 = new JScrollPane(formMovieInclude, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        formMovie.add(jsp2);
        formMovieInclude.revalidate();
        formMovie.revalidate();

        modifyMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                movie.setNews(news.isSelected());
                service.update(movie);
                for (var i = 0; i < copiesMovie.size(); i++) {
                    copiesMovie.get(i).setQuantity(Integer.parseInt(field.get(i).getText()));
                    service.update(copiesMovie.get(i));
                }
            }
        });
    }

    public static void main(String[] args) {
        createFrame();
    }

    public static void createFrame() {
        var home = new JFrame("videotron");
        var session = HibernateUtils.getSessionFactory();
        var movieService = new MovieService(session);
        var addMovie = new ModifyAMovie(new Service(session), new Movie("8888888888", true, new HashSet<>()));
        home.getContentPane().add(addMovie.panel1);
        //home.getContentPane().add(e.get(1));
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}
