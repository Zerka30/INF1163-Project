package entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {
    @Id
    private String name;

    @ManyToMany(mappedBy = "categories")
    Set<Movie> movies;

    public Category() {}

    public Category(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
