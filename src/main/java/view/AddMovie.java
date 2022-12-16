package view;

import entity.Category;
import entity.CopyMovie;
import entity.Movie;
import model.MovieService;
import model.Service;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class AddMovie {
    private JPanel formAddMovie;
    private JTextField titleField;

    private JCheckBox news;
    private JButton buttonAddMovie;
    private JPanel categoriesList;
    private JPanel categoriesList2;
    private JPanel support;
    private JPanel supportInformation;
    private JTextField blueRayQuantity;

    private JLabel blueRayLabel;
    private JLabel dvdLabel;

    private JTextField blueRayPrice;
    private JTextField dvdQuantity;
    private JTextField dvdPrice;


    private List<JCheckBox> categoriesCheckBox;
    private final MovieService movieService;
    private final Service service;

    public AddMovie(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory);
        this.movieService = new MovieService(sessionFactory);
        this.service = new Service(sessionFactory);

        drawCategories();
        drawSupport();
        addMovie();
        formAddMovie.revalidate();
    }

    private void drawCategories() {
        var categories = movieService.getCategories();
        var grid = new GridLayout(categories.size(), 1);
        categoriesCheckBox = new ArrayList<>();
        categoriesList2.setLayout(grid);
        for (var category : categories) {
            var checkBox = new JCheckBox(category.getName());
            categoriesList2.add(checkBox);
            categoriesCheckBox.add(checkBox);
        }
        var jsp = new JScrollPane(categoriesList2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        categoriesList.add(jsp);
        categoriesList2.revalidate();
        categoriesList.revalidate();

    }

    private void drawSupport() {
        var grid2 = new GridLayout(3, 3);
        supportInformation.setLayout(grid2);
        var supportLabel = new JLabel("Support");
        var quantityLabel = new JLabel("Quantité");
        var priceLabel = new JLabel("Price");

        blueRayLabel = new JLabel("blueray");
        blueRayQuantity = new JTextField("0");
        blueRayPrice = new JTextField("0");

        dvdLabel = new JLabel("dvd");
        dvdQuantity = new JTextField("0");
        dvdPrice = new JTextField("0");

        // Columns
        supportInformation.add(supportLabel);
        supportInformation.add(quantityLabel);
        supportInformation.add(priceLabel);

        // Blueray
        supportInformation.add(blueRayLabel);
        supportInformation.add(blueRayQuantity);
        supportInformation.add(blueRayPrice);
        //Dvd
        supportInformation.add(dvdLabel);
        supportInformation.add(dvdQuantity);
        supportInformation.add(dvdPrice);

        var jsp2 = new JScrollPane(supportInformation, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        support.add(jsp2);
        supportInformation.revalidate();
        support.revalidate();
    }

    private void addMovie() {
        buttonAddMovie.addActionListener(actionEvent -> {
            var categories = new HashSet<Category>();
            for (var checkbox : categoriesCheckBox) {
                if (checkbox.isSelected()) {
                    categories.add(new Category(checkbox.getText()));
                }
            }
            var movie = new Movie(titleField.getText(), news.isSelected(), categories);
            var copyMovie = new CopyMovie(Integer.parseInt(blueRayQuantity.getText()), Integer.parseInt(blueRayPrice.getText()), blueRayLabel.getText(), movie);
            var copyMovie2 = new CopyMovie(Integer.parseInt(dvdQuantity.getText()), Integer.parseInt(dvdPrice.getText()), dvdLabel.getText(), movie);

            service.save(movie);
            service.save(copyMovie);
            service.save(copyMovie2);
        });
    }

    public JPanel getPanelWindow() {
        return formAddMovie;
    }
    public void removeAll() {
        formAddMovie.removeAll();
    }
}
