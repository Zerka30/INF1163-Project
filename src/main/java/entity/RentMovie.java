package entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "rent_movie")
public class RentMovie {
    @EmbeddedId
    private RentMovieKey rentMovieId;

    @ManyToOne
    @MapsId("copyMovie")
    private CopyMovie copyMovie;

    @ManyToOne
    @MapsId("member")
    private Member member;

    @Basic
    @Temporal(TemporalType.DATE)
    private Calendar rentBackDate;

    @Basic
    @Temporal(TemporalType.DATE)
    private Calendar rentBackUser;
    private int price;

    public RentMovie() {    }

    public RentMovie(RentMovieKey rentMovieId, CopyMovie copyMovie, Member member, Calendar rentBackDate, Calendar rentBackUser, int price) {
        this.rentMovieId = rentMovieId;
        this.copyMovie = copyMovie;
        this.member = member;
        this.rentBackDate = rentBackDate;
        this.rentBackUser = rentBackUser;
        this.price = price;
    }

    public RentMovieKey getRentMovieId() {
        return rentMovieId;
    }

    public CopyMovie getCopyMovie() {
        return copyMovie;
    }

    public Member getMember() {
        return member;
    }

    public Calendar getRentBackDate() {
        return rentBackDate;
    }

    public Calendar getRentBackUser() {
        return rentBackUser;
    }
}
