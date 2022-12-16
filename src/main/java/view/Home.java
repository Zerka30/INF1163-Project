package view;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Home extends JFrame {
    private final SessionFactory sessionFactory;
    private AddMovie addMovie;
    private RentMovie rentMovie;
    private SearchModifyMovie searchModifyMovie;
    private FirstPage firstPage;
    private InformationMovie informationMovie;

    private BackMovie backMovie;
    private boolean admin;

    public Home(String title, int width, int height, SessionFactory sessionFactory) {
        Objects.requireNonNull(title);
        if (width < 0 || height < 0)
            throw new IllegalArgumentException();
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        // Init
        firstPage = new FirstPage(sessionFactory);
        changePanel(firstPage.getWindow());

        initMenu();
        specificationOfFrame(title, width, height);
    }

    private void specificationOfFrame(String title, int width, int height) {
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    private void initMenu() {
        var menubar = new JMenuBar();
        var menu = new JMenu("Menu");
        var firstMenu = new JMenuItem("Accueil");
        var informationMovieMenu = new JMenuItem("Information sur les films");
        var rentMovieMenu = new JMenuItem("Louer un film");
        var createMember = new JMenuItem("Créer un membre");
        var modifyMovieMenu = new JMenuItem("Modifier un film");
        var adminMenu = new JMenuItem("Administration");
        var addMovieMenu = new JMenuItem("Ajouter un film");
        var backMovieMenu = new JMenuItem("Rendre  un film");
        var disconnectMenu = new JMenuItem("Déconnexion");
        menubar.add(menu);
        menu.add(firstMenu);
        menu.add(informationMovieMenu);
        menu.add(rentMovieMenu);
        menu.add(backMovieMenu);
        menu.add(createMember);

        if (admin) {
            menu.add(addMovieMenu);
            menu.add(modifyMovieMenu);
            menu.add(disconnectMenu);
        } else {
            menu.add(adminMenu);
        }
        //
        setJMenuBar(menubar);


        firstMenu.addActionListener(actionEvent -> {
            firstPage = new FirstPage(sessionFactory);
            changePanel(firstPage.getWindow());
        });
        informationMovieMenu.addActionListener(actionEvent -> {
            informationMovie = new InformationMovie(sessionFactory);
            changePanel(informationMovie.getPanelWindow());
        });
        addMovieMenu.addActionListener(actionEvent -> {
            addMovie = new AddMovie(sessionFactory);
            changePanel(addMovie.getPanelWindow());
        });
        rentMovieMenu.addActionListener(actionEvent -> {
            rentMovie = new RentMovie(sessionFactory);
            changePanel(rentMovie.getPanelWindow());
        });
        modifyMovieMenu.addActionListener(actionEvent -> {
            searchModifyMovie = new SearchModifyMovie(sessionFactory);
            changePanel(searchModifyMovie.getPanelWindow());
        });

        disconnectMenu.addActionListener(actionEvent -> {
            admin = false;
            initMenu();
            changePanel(firstPage.getWindow());
        });

        backMovieMenu.addActionListener(actionEvent -> {
            backMovie = new BackMovie(sessionFactory);
            changePanel(backMovie.getWindow());
        });


        adminMenu.addActionListener(actionEvent -> {
            var jop = new JOptionPane();
            while (true) {
                String password = jop.showInputDialog(null, "Mot de passe", "Administration", JOptionPane.QUESTION_MESSAGE);
                if (password.equals("1234")) {
                    admin = true;
                    initMenu();
                    revalidate();
                    break;
                } else {
                    jop.showMessageDialog(null, "Mauvais mot de passe", "Authentification échoué", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
        revalidate();
    }
}