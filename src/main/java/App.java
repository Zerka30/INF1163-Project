import entity.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.PersonService;

public class App {
    public static void main(String[] args) {
        var sessionFactory = getSessionFactory();
        var personService = new PersonService(sessionFactory);
        var person = new Person("VALADE", "Jeremy");

        personService.addPerson(person);
    }

    public static SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
