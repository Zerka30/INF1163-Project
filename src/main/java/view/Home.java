package view;

import model.MovieService;
import model.Service;
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

    public Home(String title, int width, int height, SessionFactory sessionFactory) {
        Objects.requireNonNull(title);
        if (width < 0 || height < 0)
            throw new IllegalArgumentException();
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        // Init
        firstPage = new FirstPage(sessionFactory);
        changePanel(firstPage.getWindow());

        initPages();
        initMenu();
        specificationOfFrame(title, width, height);
    }

   private void initPages() {
        addMovie = new AddMovie(sessionFactory);
        rentMovie = new RentMovie(new Service(sessionFactory));
        searchModifyMovie = new SearchModifyMovie(new MovieService(sessionFactory), sessionFactory);
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

    private class MenuAction implements ActionListener {

        private JPanel panel;

        private MenuAction(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(panel);
        }
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
        menubar.add(menu);
        menu.add(firstMenu);
        menu.add(informationMovieMenu);
        menu.add(rentMovieMenu);
        menu.add(createMember);
        menu.add(adminMenu);
        menu.add(addMovieMenu);
        menu.add(modifyMovieMenu);
        //
        setJMenuBar(menubar);

        firstMenu.addActionListener(actionEvent -> {
            firstPage = new FirstPage(sessionFactory);
            changePanel(firstPage.getWindow());
        });

        informationMovieMenu.addActionListener(actionEvent -> {
            informationMovie = new InformationMovie(sessionFactory);
            changePanel(informationMovie.getWindow());
        });

        addMovieMenu.addActionListener(actionEvent -> {
            addMovie = new AddMovie(sessionFactory);
            changePanel(addMovie.getPanelWindow());
        });


        rentMovieMenu.addActionListener(new MenuAction(rentMovie.getPanelWindow()));
        modifyMovieMenu.addActionListener(new MenuAction(searchModifyMovie.getPanelWindow()));

        modifyMovieMenu.addActionListener(actionEvent -> {
            searchModifyMovie = new SearchModifyMovie(new MovieService(sessionFactory), sessionFactory);
            changePanel(searchModifyMovie.getPanelWindow());
        });
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
    }
}
