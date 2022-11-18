package entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "Personw")
public class Person {
    @Id
    private String name;
    private String firstname;

    public Person() {}

    public Person(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
    }

}
