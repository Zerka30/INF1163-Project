package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Person;
import service.PersonService;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void addPerson() {
        var sessionFactory = MySessionFactory.createSessionFactory();
        var personService = new PersonService(sessionFactory);
        var person = new Person("VALADE", "Jeremy");

        personService.addPerson(person);
        var persons = personService.getPersons();
        assertTrue(persons.contains(person));
    }
}
