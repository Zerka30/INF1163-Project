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

    public Home(String title, int width, int height, SessionFactory sessionFactory) {
        Objects.requireNonNull(title);
        if (width < 0 || height < 0)
            throw new IllegalArgumentException();
        this.sessionFactory = Objects.requireNonNull(sessionFactory);

        initPages();
        initMenu();
        specificationOfFrame(title, width, height);
    }

    private void initPages() {
        addMovie = new AddMovie(new MovieService(sessionFactory), new Service(sessionFactory));
        rentMovie = new RentMovie(new Service(sessionFactory));
        searchModifyMovie = new SearchModifyMovie(new MovieService(sessionFactory), sessionFactory);
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
        var homePage = new JMenuItem("Accueil");
        var informationMovie = new JMenuItem("Information sur les films");
        var rentMovieMenu = new JMenuItem("Louer un film");
        var createMember = new JMenuItem("Créer un membre");
        var modifyMovieMenu = new JMenuItem("Modifier un film");
        var adminMenu = new JMenuItem("Administration");
        var addMovieMenu = new JMenuItem("Ajouter un film");
        menubar.add(menu);
        menu.add(homePage);
        menu.add(informationMovie);
        menu.add(rentMovieMenu);
        menu.add(createMember);
        menu.add(adminMenu);
        menu.add(addMovieMenu);
        menu.add(modifyMovieMenu);
        //
        setJMenuBar(menubar);

        addMovieMenu.addActionListener(new MenuAction(addMovie.getPanelWindow()));
        rentMovieMenu.addActionListener(new MenuAction(rentMovie.getPanelWindow()));
        modifyMovieMenu.addActionListener(new MenuAction(searchModifyMovie.getPanelWindow()));
       // var session = HibernateUtils.getSessionFactory();
        //var test = new SearchModifyMovie(new MovieService(session), session);

        //menu.addActionListener(new MenuAction(test.getAll()));
      //  menuItem1.addActionListener(new MenuAction(test.getAll()));
      //  informationMovie.addActionListener(new MenuAction(test.getAll()));
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
        repaint();
    }
}
