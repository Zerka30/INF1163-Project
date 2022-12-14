package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private String title;

    private boolean news;

    //   @ManyToMany(mappedBy = "movies")
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "category_movie",
            joinColumns = {@JoinColumn(name = "title")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<Category> categories;

    public Movie() {
    }

    public Movie(String title, boolean news, Set<Category> categories) {
        this.title = Objects.requireNonNull(title);
        this.news = news;
        this.categories = new HashSet<>(categories);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", news=" + news +
                ", categories=" + categories +
                '}';
    }
}
