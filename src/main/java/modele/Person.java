package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    private final String name;
    private final String firstname;

    public Person(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
    }
}
