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

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public Set<Category> getCategories() {
        return categories;
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
