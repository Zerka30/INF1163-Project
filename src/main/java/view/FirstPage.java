package view;

import model.Service;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FirstPage {
    private JPanel window;
    private JPanel rentPanel;
    private JPanel rentPanelInclude;

    private final Service service;

    public FirstPage(SessionFactory sessionFactory) {

        this.service = new Service(sessionFactory);
        drawTableRent();
    }

    private void drawTableRent() {
        var rentMoviesLate = service.getRentMovieLate();

        var grid = new GridLayout(rentMoviesLate.size() + 1, 5);
        rentPanelInclude.setLayout(grid);

        // Columns name
        rentPanelInclude.add(new JLabel("Identifiant du membre"));
        rentPanelInclude.add(new JLabel("Film"));
        rentPanelInclude.add(new JLabel("Date de location"));
        rentPanelInclude.add(new JLabel("Date de rendu prévu"));
        rentPanelInclude.add(new JLabel("Nombre de jours de retards"));

        for (var rentMovieLate : rentMoviesLate) {
            var id = rentMovieLate.getCopyMovie().getId();
            var formatDate = new SimpleDateFormat("yyyy-MM-dd");
            var rentDateCalendar = rentMovieLate.getRentMovieId().getRentDate();
            var rentDateBackCalendar = rentMovieLate.getRentBackDate();
            var rentDate = formatDate.format(rentDateCalendar.getTime());
            var rentDateBack = formatDate.format(rentDateBackCalendar.getTime());
            var numberDiffDay = (Calendar.getInstance().getTimeInMillis() - rentDateBackCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000);

            rentPanelInclude.add(new JLabel(rentMovieLate.getRentMovieId().getMemberId()));
            rentPanelInclude.add(new JLabel(service.getMovieFromCopyMovie(id)));

            rentPanelInclude.add(new JLabel(rentDate));
            rentPanelInclude.add(new JLabel(rentDateBack));
            rentPanelInclude.add(new JLabel(String.valueOf(numberDiffDay)));
        }
        var jsp = new JScrollPane(rentPanelInclude, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rentPanel.add(jsp);
        rentPanelInclude.revalidate();
        rentPanel.revalidate();
    }

    public JPanel getPanelWindow() {
        return window;
    }

}
