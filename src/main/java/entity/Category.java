package entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {
    @Id
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinTable(
            name = "category_movie",
            joinColumns = { @JoinColumn(name = "category_id") },
            inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )
    Set<Movie> movies;

    public Category() {}

    public Category(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }
}
