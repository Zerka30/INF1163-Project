import entity.Member;
import model.MemberService;
import model.MovieService;
import view.SearchMovieToModify;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        var sessionFactory = HibernateUtils.getSessionFactory();
        var searchMovie = new SearchMovieToModify(sessionFactory);



        //var memberajout=new Member("0681300200","michouville ",65 , "inoxtag");

        HibernateUtils.getSessionFactory();

        var memberservicebis=new MemberService(sessionFactory);
        var removemoviebis=new MovieService(sessionFactory);
        //memberservicebis.addMember(memberajout);


        var toto=removemoviebis.getrentMovies(1,"345");
        toto.setPrice(90);
        System.out.println("ca passe");
        removemoviebis.updaterentMovie(toto);
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



