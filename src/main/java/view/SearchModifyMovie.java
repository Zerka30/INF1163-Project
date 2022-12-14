package view;

import controller.HibernateUtils;
import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SearchModifyMovie {
    static JFrame home;
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
                   // home.dispose();
                    home.removeAll();
                    test(sessionFactory);
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
    public static void main(String[] args) {
        var session = HibernateUtils.getSessionFactory();
        var test = new SearchModifyMovie(new MovieService(session), session);

        var menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0,1));
        var menu = new JMenu("Ajouter un film");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


        var menuModifier = new JMenu("Modifier un film");
        menuBar.add(menu);
        menuBar.add(menuModifier);

        home = new JFrame("videotron");
        home.setJMenuBar(menuBar);
        home.setLayout(new GridLayout(1,2));
        home.getContentPane().add(test.all,  BorderLayout.EAST);
        home.setPreferredSize(new Dimension(500, 200));
        home.setLocationRelativeTo(null);
        home.setResizable(false);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }

    public static void test(SessionFactory sessionFactory) {
        var test = new SearchModifyMovie(new MovieService(sessionFactory), sessionFactory);
        //home = new JFrame("videotron 2");
        home.getContentPane().add(test.all);
        home.revalidate();
        home.repaint();
        //home.setPreferredSize(new Dimension(750, 1000));
        //home.setLocationRelativeTo(null);
        //home.setResizable(false);
        //home.pack();
        //home.setLocationRelativeTo(null);
        //home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //home.setVisible(true);
    }
}
