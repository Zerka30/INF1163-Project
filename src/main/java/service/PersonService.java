package service;

import modele.Person;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class PersonService {
    private final SessionFactory sessionFactory;

    public PersonService(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}
