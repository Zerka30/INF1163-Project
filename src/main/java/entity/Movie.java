package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private String title;
    String support;
    String category;
    int quantity;
    private boolean isNew;

    @ManyToMany(mappedBy = "movies")
    private Set<Category> categories;
    public Movie() {}
    public Movie(String name, int quantity, String support, String category, Boolean isNew)
    {
        this.title = title;
        this.quantity = quantity;
        this.support = support;
        this.category = category;
        this.isNew = isNew;
    }
    public String getTitle() {
        return title;
    }
}
