package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private String title;

    private boolean news;

    @ManyToMany(mappedBy = "movies")
    private Set<Category> categories;
    public Movie() {}

    public String getTitle() {
        return title;
    }
}
