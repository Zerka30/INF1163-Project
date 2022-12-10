package view;

import controller.Hello;
import controller.HibernateUtils;
import entity.Movie;
import model.MovieService;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchMovieToModify {

    /**
     * This class can to get movies of database
     */
    private MovieService movieService;

    /**
     *
     */
    private List<Movie> movies;
    private JPanel window;
    private JTextField title;
    private JButton search;
    private JPanel result;
    private JButton button1;
    private JTable table1;

    public SearchMovieToModify(SessionFactory sessionFactory) {
        this.movieService = new MovieService(Objects.requireNonNull(sessionFactory));
        var columns = new String[]{"title", "modifier"};
        movies = movieService.getMovies(null);
        System.out.println("size movies " + movies.size());
        Object[][] data = new Object[movies.size()][2];

        for (var i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            data[i][0] = movie.getTitle();

            var button = new JButton("Modifier");
            button.addActionListener(actionEvent -> {
                // ouvre la fenêtre de modification
            });
            data[i][1] = "hello";

        }
        //System.out.println(data[0] + " " + columns);

        table1 = new JTable(data, columns);
        var pane = new JScrollPane(table1);
        var nbColumns = new TableColumn(2);
     //   tabMovies.addColumn(nbColumns);
        //tabMovies.setValueAt("hello", 0, 2);
        result = new JPanel();
        result.setSize(new Dimension(400, 400));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //var list = movieService.getMovies(title.getText());
               // System.out.println("PASS SEARCH " + list);
                result.add(new JButton("hello"));
                result.setVisible(true);
                title.setText("hello world");
                result.add(new JButton("else"));
                result.updateUI();
                result.repaint();
            }
        });
    }

    public JPanel getWindow() {
        return window;
    }

    public List<Component> getPanel() {
        var pane = new JScrollPane(table1);
        return Arrays.asList(window);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.movieService = new MovieService(Objects.requireNonNull(HibernateUtils.getSessionFactory()));
        movies = movieService.getMovies(null);
        var columns = new String[]{"title", "modifier"};
        Object[][] data = new Object[1][2];
        for (var i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            data[i][0] = movie.getTitle();

            var button = new JButton("Modifier");
            button.addActionListener(actionEvent -> {
                // ouvre la fenêtre de modification
            });
            data[i][1] = button;

        }
        //table2 = new JTable(data, columns);
        result = new JPanel();
        result.add(new JButton("hello"));

    }
    public static void main(String[] args) {

    }
}
