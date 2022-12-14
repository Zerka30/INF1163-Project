package entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {
    @Id
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "category_movie",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    Set<Movie> movies;
}
