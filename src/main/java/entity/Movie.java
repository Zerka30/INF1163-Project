package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int quantity;
    private String title;
    private String support;
    private String category;
    private boolean isNew;

    @ManyToMany(mappedBy = "movies")
    private Set<Category> categories;
    public Movie() {}
    public Movie(String title, int quantity, String support, String category, Boolean isNew)
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
