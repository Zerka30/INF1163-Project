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
    private JPanel rentPanel2;

    private final Service service;

    public FirstPage(SessionFactory sessionFactory) {
        this.service = new Service(sessionFactory);
        drawTableRent();
    }

    private void drawTableRent() {
        var rentMoviesLate = service.getRentMovieLate();

        var grid = new GridLayout(rentMoviesLate.size() + 1, 5);
        rentPanel2.setLayout(grid);

        // Columns name
        rentPanel2.add(new JLabel("Identifiant du membre"));
        rentPanel2.add(new JLabel("Film"));
        rentPanel2.add(new JLabel("Date de location"));
        rentPanel2.add(new JLabel("Date de rendu prévu"));
        rentPanel2.add(new JLabel("Nombre de jours de retards"));

        for (var rentMovieLate : rentMoviesLate) {
            var id = rentMovieLate.getCopyMovie().getId();
            var formatDate = new SimpleDateFormat("yyyy-MM-dd");
            var rentDateCalendar = rentMovieLate.getRentMovieId().getRentDate();
            var rentDateBackCalendar = rentMovieLate.getRentBackDate();
            var rentDate = formatDate.format(rentDateCalendar.getTime());
            var rentDateBack = formatDate.format(rentDateBackCalendar.getTime());
            var numberDiffDay = (Calendar.getInstance().getTimeInMillis() - rentDateBackCalendar.getTimeInMillis()) / (24*60*60*1000);

            rentPanel2.add(new JLabel(rentMovieLate.getRentMovieId().getMemberId()));
            rentPanel2.add(new JLabel(service.getMovieFromCopyMovie(id)));

            rentPanel2.add(new JLabel(rentDate));
            rentPanel2.add(new JLabel(rentDateBack));
            rentPanel2.add(new JLabel(String.valueOf(numberDiffDay)));
        }
        var jsp = new JScrollPane(rentPanel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rentPanel.add(jsp);
        rentPanel2.revalidate();
        rentPanel.revalidate();
    }

    public JPanel getWindow() {
        return window;
    }
}
