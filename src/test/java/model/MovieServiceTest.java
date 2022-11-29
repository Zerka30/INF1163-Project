package model;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Test
    void getMovies() {

    }
}