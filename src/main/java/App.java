import view.SearchMovieToModify;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        var sessionFactory = HibernateUtils.getSessionFactory();
        var searchMovie = new SearchMovieToModify(sessionFactory);

        createFrame(searchMovie.getWindow());
     /*   var personService = new PersonService(sessionFactory);
        var person = new Person("VALADE 6", "Jeremy");

       // personService.addPerson(person);
        var list = personService.getPersons();
        list.forEach(System.out::println);
        //System.out.println(person);*/
    }

    public static void createFrame(Component e) {
        var home = new JFrame("videotron");
        home.getContentPane().add(e);
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }


}
/*
Commentaire à la con */