import controller.HibernateUtils;
import entity.Member;
import model.MemberService;
import model.Service;
import view.Home;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        //var app = new Home("Videotron", 500, 500);


      /*  var sessionFactory = HibernateUtils.getSessionFactory();
        var searchMovie = new SearchMovieToModify(sessionFactory);
      //  System.out.println("hello " + searchMovie.getWindow());
        createFrame(searchMovie.getPanel());
        //Entity toto = new Member();
        var member = new Member("888888","55" , 55, "545" );
        var service = new Service(sessionFactory);
        //service.add(member);
        //Class<? extends Entity> e;

       /* Object s = new String();
        System.out.println();
        Arrays.stream(s.getClass().getDeclaredAnnotations()).forEach(System.out::println);
        System.out.println("------------------");
        Arrays.stream(Member.class.getAnnotatedInterfaces()).forEach(System.out::println);
        Arrays.stream(Member.class.getInterfaces()).forEach(System.out::println);*/


     /*   var personService = new PersonService(sessionFactory);
        var person = new Person("VALADE 6", "Jeremy");

       // personService.addPerson(person);
        var list = personService.getPersons();
        list.forEach(System.out::println);
        //System.out.println(person);*/
    }

    public static void createFrame(java.util.List<Component> e) {
        var home = new JFrame("videotron");
        System.out.println(e);
        home.getContentPane().add(e.get(0));
        //home.getContentPane().add(e.get(1));
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }


}
