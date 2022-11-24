import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        var sessionFactory = getSessionFactory();
     /*   var personService = new PersonService(sessionFactory);
        var person = new Person("VALADE 6", "Jeremy");

       // personService.addPerson(person);
        var list = personService.getPersons();
        list.forEach(System.out::println);
        //System.out.println(person);*/
    }

    public static SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
