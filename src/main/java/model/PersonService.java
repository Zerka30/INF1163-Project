package model;

import entity.Person;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Objects;

public class PersonService {
    private final SessionFactory sessionFactory;

    public PersonService(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public void addPerson(Person person) {
        Objects.requireNonNull(person);
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }
    public List<Person> getPersons() {
        var session = sessionFactory.openSession();
        var persons = session.createQuery("from Person").list();
        session.clear();
        return  persons;
    }
}
