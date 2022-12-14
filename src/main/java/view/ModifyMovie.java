package view;

import entity.Movie;
import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifyMovie {

    //CONSTRUCTEUR
    public ModifyMovie(MovieService ms, SessionFactory sessionFactory){
        modifyMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modifier film clicked");
                newSupport = supportComboBox.getSelectedItem().toString();
                newQuantity = stringToInt(quantityTextField.getText());
                newIsNew = isNewCheckBox.isSelected();

                List<Movie> listMovie = ms.getMovies(titleLabel.getText());

                for (Movie movie : listMovie) {
                    if (movie.getTitle().equals(quantityTextField.getText())) {
                        ms.modifyMovie(movie);
                    } else {
                        System.out.println("error");
                    }

                }
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
    //ATTRIBUTS
    String newSupport;
    Boolean newIsNew;
    int newQuantity;
    private JComboBox supportComboBox;
    private JCheckBox isNewCheckBox;
    private JTextField quantityTextField;
    private JPanel mainPanel;
    private JButton modifyMovieButton;
    private JLabel titleLabel;

}
