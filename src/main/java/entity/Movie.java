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
    public Movie(String title, Boolean isNew)
    {
        this.title = title;
        this.news = isNew;
    }
    public String getTitle() {
        return title;
    }
}
