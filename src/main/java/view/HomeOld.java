package view;

import model.MovieService;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.util.Objects;

public class HomeOld {
    private JFrame app;
    private boolean isAdmin;

    private SessionFactory sessionFactory;
    public HomeOld(String titleApp, int width, int height, SessionFactory sessionFactory) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Width or height not inferior to zero");

        this.sessionFactory = Objects.requireNonNull(sessionFactory);

        isAdmin = false;
        app = new JFrame(titleApp);
        createFrame(width, height);



    }

    private void createFrame(int width, int height) {
        app.setJMenuBar(createJMenuBar());
        app.setPreferredSize(new Dimension(width, height));
        app.setLocationRelativeTo(null);
        app.setResizable(false);
        app.pack();
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    private JMenuBar createJMenuBar() {
        var menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0, 1));
        // list items of menu
        var app = new JMenu("Accueil");
        var addMovie = new JMenu("Ajouter un film");
        var modifyMovie = new JMenu("Modifier un film");
        var informationAboutMovies = new JMenu("Informations sur les films");
        var backMovie = new JMenu("Rendre un film");
        var createMember = new JMenu("Créer un membre");
        var administration = new JMenu("Administration");
        var jmenuItemn = new JMenuItem("tesy");

        //jmenuItemn.
        administration.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent menuEvent) {
                var admin = new ConnectToAdmin();
            }

            @Override
            public void menuDeselected(MenuEvent menuEvent) {

            }

            @Override
            public void menuCanceled(MenuEvent menuEvent) {

            }
        });

        modifyMovie.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent menuEvent) {
                var searchModiy = new SearchModifyMovie(new MovieService(sessionFactory), sessionFactory);
            }

            @Override
            public void menuDeselected(MenuEvent menuEvent) {

            }

            @Override
            public void menuCanceled(MenuEvent menuEvent) {

            }
        });


        var disconnect = new JMenu("Déconnexion");

        menuBar.add(app);
        menuBar.add(informationAboutMovies);
        menuBar.add(backMovie);
        menuBar.add(createMember);

        if (!isAdmin)
            menuBar.add(administration);
        if (isAdmin) {
            menuBar.add(addMovie);
            menuBar.add(modifyMovie);
            menuBar.add(disconnect);
        }

        return menuBar;
    }
}
