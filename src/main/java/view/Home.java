package view;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Home extends JFrame {
    private final SessionFactory sessionFactory;
    private AddMovie addMovie;
    private RentMovie rentMovie;
    private SearchModifyMovie searchModifyMovie;
    private FirstPage firstPage;
    private InformationMovie informationMovie;

    private CreateMember createMember;
    private BackMovie backMovie;
    private boolean admin;
    private final String password;

    public Home(String title, int width, int height, SessionFactory sessionFactory) {
        Objects.requireNonNull(title);
        if (width < 0 || height < 0)
            throw new IllegalArgumentException();
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        password = initPasswordAdmin();
        firstPage = new FirstPage(sessionFactory);
        changePanel(firstPage.getPanelWindow());
        initPanels();
        initMenu();
        specificationOfFrame(title, width, height);

    }

    private void initPanels() {
        addMovie = new AddMovie(sessionFactory);
        rentMovie = new RentMovie(sessionFactory);
        searchModifyMovie = new SearchModifyMovie(sessionFactory);
        informationMovie = new InformationMovie(sessionFactory);
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
        var createMemberMenu = new JMenuItem("Créer un membre");
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
        menu.add(createMemberMenu);

        if (admin) {
            menu.add(addMovieMenu);
            menu.add(modifyMovieMenu);
            menu.add(disconnectMenu);
        } else {
            menu.add(adminMenu);
        }
        setJMenuBar(menubar);


        firstMenu.addActionListener(actionEvent -> {
            firstPage = new FirstPage(sessionFactory);
            changePanel(firstPage.getPanelWindow());
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

        createMemberMenu.addActionListener(actionEvent -> {
            createMember = new CreateMember(sessionFactory);
            changePanel(createMember.getPanelWindow());
        });

        disconnectMenu.addActionListener(actionEvent -> {
            admin = false;
            initMenu();
            changePanel(firstPage.getPanelWindow());
        });

        backMovieMenu.addActionListener(actionEvent -> {
            backMovie = new BackMovie(sessionFactory);
            changePanel(backMovie.getWindow());
        });


        adminMenu.addActionListener(actionEvent -> {
            var jop = new JOptionPane();
            while (true) {
                String passwordEnter = jop.showInputDialog(null, "Mot de passe", "Administration", JOptionPane.QUESTION_MESSAGE);
                if (passwordEnter.equals(password)) {
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
    private String initPasswordAdmin() {
        var properties = new Properties();
        try {
            var input = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("program.cfg")).getFile());
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("passe");
            return "1234";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty("password");
    }

}