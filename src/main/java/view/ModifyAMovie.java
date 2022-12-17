package view;

import entity.Movie;
import model.Service;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ModifyAMovie extends JFrame {
    private JPanel window;
    private JPanel formMovie;
    private JPanel formMovieInclude;
    private JButton modifyMovie;
    private JCheckBox news;
    private JLabel titleLabel;

    private final Service service;
    private final Movie movie;

    public ModifyAMovie(SessionFactory sessionFactory, Movie movie) {
        Objects.requireNonNull(sessionFactory);
        this.service = new Service(sessionFactory);
        this.movie = Objects.requireNonNull(movie);
        features();
        initTabForSupport();
        titleLabel.setText(movie.getTitle());
        news.setSelected(movie.isNews());
        news.revalidate();
        getContentPane().add(window, BorderLayout.CENTER);
        window.revalidate();
    }

    private void features() {
        setResizable(false);
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setVisible(true);
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

        modifyMovie.addActionListener(actionEvent -> {
            movie.setNews(news.isSelected());
            service.update(movie);
            for (var i = 0; i < copiesMovie.size(); i++) {
                copiesMovie.get(i).setQuantity(Integer.parseInt(field.get(i).getText()));
                service.update(copiesMovie.get(i));
                dispose();
            }
        });
    }

}
