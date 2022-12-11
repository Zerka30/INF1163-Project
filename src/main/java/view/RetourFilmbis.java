package view;

import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RetourFilmbis extends JFrame {
    private JPanel Retourfilmpanel;
    private JTextField texttest;
    private JLabel Labeltest;
    private JButton buttontest;
    private JLabel labeltest2;
    private final MovieService movieService;

    public int valeurprix;

    public  RetourFilmbis(String title, SessionFactory sessionFactory) {
        super(title);
        this.movieService = new MovieService(Objects.requireNonNull(sessionFactory));


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Retourfilmpanel);
        this.pack();

        buttontest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //grab the text
                int textrecup=(int)Double.parseDouble(texttest.getText());

                valeurprix=textrecup;

                System.out.println(valeurprix);

                labeltest2.setText("La valeur est changé par "+textrecup);
                var toto=movieService.getrentMovies(1,"345");
                toto.setPrice(valeurprix);
                System.out.println("ca passe");
                System.out.println("ici"+valeurprix);
                movieService.updaterentMovie(toto);




            }

        });

    }


    public int getValeur() {
        return valeurprix;
    }




}
