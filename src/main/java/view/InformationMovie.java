package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import model.Service;
import org.hibernate.SessionFactory;
import javax.swing.*;
import java.awt.*;

public class InformationMovie {
    private JPanel window;
    private JTextField movieText;
    private JButton buttonSearch;
    private JPanel result;
    private JPanel result2;

    private final Service service;

    public InformationMovie(SessionFactory sessionFactory) {
        this.service = new Service(sessionFactory);
        drawTable(null);
        buttonSearch.addActionListener(actionEvent -> drawTable(movieText.getText()));
    }

    private void drawTable(String title) {
        var movies = service.getMovies(title);
        result.removeAll();
        result2.removeAll();

        var grid = new GridLayout(movies.size() + 1, 2);
        result2.setLayout(grid);
        result2.add(new JLabel("Film"));
        result2.add(new JLabel("Catégories"));

        for (var movie : movies) {
            var titleLabel = new JLabel(movie.getTitle());
            titleLabel.setOpaque(true);
            titleLabel.setSize(new Dimension(100, 20));
            var categories = service.getCategoriesFromMovie(movie);
            var categoriesText = new JLabel(String.join(",", categories));
            result2.add(titleLabel);
            result2.add(categoriesText);
        }
        var jsp = new JScrollPane(result2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        result.add(jsp);
        result2.revalidate();
        result.revalidate();
    }

    public JPanel getPanelWindow() {
        return window;
    }

}
