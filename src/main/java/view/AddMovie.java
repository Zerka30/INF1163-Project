package view;

import controller.HibernateUtils;
import entity.Category;
import entity.CopyMovie;
import entity.Movie;
import model.MovieService;
import model.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class AddMovie {
    private JPanel formAddMovie;
    private JTextField titleField;
    private JLabel title;
    private JLabel categories;
    private JTable supportTable;
    private JCheckBox news;
    private JButton buttonAddMovie;
    private JPanel categoriesList;
    private JPanel categoriesList2;
    private JPanel support;
    private JPanel supportInformation;

    private MovieService movieService;
    private Service service;
    public AddMovie(MovieService movieService, Service service) {
        this.movieService = Objects.requireNonNull(movieService);
        this.service = Objects.requireNonNull(service);
        var categories = movieService.getCategories();

        var grid = new GridLayout(categories.size(), 1);
        var categoriesCheckBox = new ArrayList<JCheckBox>();
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

        var grid2 = new GridLayout(3, 3);
        supportInformation.setLayout(grid2);
        var supportLabel = new JLabel("Support");
        var quantityLabel = new JLabel("Quantité");
        var priceLabel = new JLabel("Price");

        var blueRayLabel = new JLabel("blueray");
        var blueRayQuantity = new JTextField("0");
        var blueRayPrice = new JTextField("0");

        var dvdLabel = new JLabel("Dvd");
        var dvdQuantity = new JTextField("0");
        var dvdPrice = new JTextField("0");

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

        formAddMovie.revalidate();

        buttonAddMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var categories = new HashSet<Category>();
                for (var checkbox : categoriesCheckBox) {
                    if (checkbox.isSelected()) {
                        categories.add(new Category(checkbox.getText()));
                    }
                }
                var movie = new Movie(titleField.getText(), news.isSelected(), categories);
                var copyMovie = new CopyMovie(Integer.parseInt(blueRayQuantity.getText()), Integer.parseInt(blueRayPrice.getText()), blueRayLabel.getText(), movie);
                var copyMovie2 = new CopyMovie(Integer.parseInt(dvdQuantity.getText()), Integer.parseInt(dvdPrice.getText()), dvdLabel.getText(), movie);

                System.out.println(movie);

                service.save(movie);
                service.save(copyMovie);
                service.save(copyMovie2);
            }
        });
    }

   public JPanel getPanelWindow() {
        return formAddMovie;
   }
}
