package entity;

import model.MovieService;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "copyMovie")
public class CopyMovie {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    // numbers of copies
    private int quantity;
    // price of the movie
    private int price;
    // support of the film (blueray | dvd)
    private String support;

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RentMovie> movies;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;

    public CopyMovie() {}

    public CopyMovie(int quantity, int price, String support, Movie movie) {
        if (quantity < 0 || price < 0)
            throw new IllegalArgumentException();
        this.quantity = quantity;
        this.price = price;
        this.support =  Objects.requireNonNull(support);
        this.movie = Objects.requireNonNull(movie);

    }

    public String getSupport() {
        return support;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
}
