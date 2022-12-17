package view;

import entity.CopyMovie;
import entity.Member;
import entity.Movie;
import entity.RentMovieKey;
import model.Service;
import org.hibernate.SessionFactory;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Objects;

public class RentMovie {
    private JPanel rentMoviePanel;
    private JTextField memberId;
    private JButton searchMember;
    private JTextField movieId;
    private JButton searchMovie;
    private JButton buttonRentMovie;
    private JLabel memberLabel;
    private JPanel getMovie;
    private JPanel supportPanel;
    private JPanel panelRent;
    private JTextField priceTextField;

    private final Service service;
    private Member member;
    private Movie movie;
    private JComboBox<Object> movieJComboBox;
    private JComboBox<Object> supportJComboBox;
    private JComboBox<Object> locationJComboBox;

    public RentMovie(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory);
        this.service = new Service(sessionFactory);
        drawWindow();
        addRentMovie();
    }

    private void drawWindow() {
        searchMember.addActionListener(actionEvent -> {
            member = service.getMember(memberId.getText());
            memberLabel.setText("<html>Identifiant : " + member.getPhoneNumber() + "<br/>Code secret : " + member.getSecretCode());
        });
        searchMovie.addActionListener(actionEvent -> {
            getMovie.removeAll();
            var movies = service.getMovies(movieId.getText());
            var moviesTitle = movies.stream().map(Movie::getTitle).toArray();

            movieJComboBox = new JComboBox<>(moviesTitle);

            movieJComboBox.addActionListener(actionEvent1 -> {
                supportPanel.removeAll();
                panelRent.removeAll();
                movie = service.getMovie(Objects.requireNonNull(movieJComboBox.getSelectedItem()).toString());
                var supports = service.getCopiesIdOfMovie(movie);

                supportJComboBox = new JComboBox<>(supports.stream().map(CopyMovie::getSupport).toArray());
                supportPanel.add(supportJComboBox);
                supportPanel.revalidate();

                String[] typeOfLocation;
                if (movie.isNews()) {
                    typeOfLocation = new String[]{"Journée"};
                } else {
                    typeOfLocation = new String[]{"Journée", "Semaine"};
                }
                locationJComboBox = new JComboBox<>(typeOfLocation);
                panelRent.add(locationJComboBox);
                panelRent.revalidate();
            });
            getMovie.add(movieJComboBox);
            getMovie.revalidate();
        });
    }

    private void addRentMovie() {
        buttonRentMovie.addActionListener(actionEvent -> {
            var copyMovie = service.getCopyMovie(Objects.requireNonNull(movieJComboBox.getSelectedItem()).toString(), Objects.requireNonNull(supportJComboBox.getSelectedItem()).toString());
            var today = Calendar.getInstance();
            var rentDate = Calendar.getInstance();
            if (Objects.requireNonNull(locationJComboBox.getSelectedItem()).toString().equals("Journée")) {
                rentDate.add(Calendar.DAY_OF_MONTH, 1);
            } else {
                rentDate.add(Calendar.DAY_OF_MONTH, 7);
            }
            var rentMovieKey = new RentMovieKey(member.getPhoneNumber(), copyMovie.getId(), rentDate);
            var rentMovie = new entity.RentMovie(rentMovieKey, copyMovie, member, today, null, Float.parseFloat(priceTextField.getText()));
            service.save(rentMovie);

            // Send a message to the member to confirm the rent
            JOptionPane.showMessageDialog(null, "Le film a été loué avec succès");

        });
    }


    public JPanel getPanelWindow() {
        return rentMoviePanel;
    }


}
