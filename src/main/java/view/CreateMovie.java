package view;

import entity.Movie;
import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMovie {

    //CONSTRUCTEUR
    public CreateMovie(MovieService ms, SessionFactory sessionFactory){

        createMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ajouer film clicked");

                title = titleTextField.getText();
                quantity = stringToInt(quantityTextField.getText());
                support = supportComboBox.getSelectedItem().toString();
                category = categoryComboBox.getSelectedItem().toString();
                isNew = isNewCheckBox.isSelected();

                Movie movie = new Movie(title,quantity,support,category,isNew);
                ms.createMovie(movie);
                System.out.println("movie Created");

            }
        });

    }

    //METHODES
    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // La chaîne de caractères ne peut pas être convertie en entier
            return 0;
        }
    }
    public JPanel getWindow() {
        return mainPanel;
    }

    //ATTRIBUTS
    private String title;
    private String support;
    private String category;
    private Boolean isNew;
    private int quantity;
    private JButton createMovieButton;
    private JCheckBox isNewCheckBox;
    private JComboBox categoryComboBox;
    private JTextField quantityTextField;
    private JComboBox supportComboBox;
    private JTextField titleTextField;
    private JPanel mainPanel;


}
