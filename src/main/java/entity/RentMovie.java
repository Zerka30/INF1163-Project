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

    private Calendar rentDate;
    private Calendar rentBackDate;
    private Calendar rentBackUser;
    private int price;

    public RentMovie() {}
    public RentMovie(RentMovieKey rentMovieId, CopyMovie copyMovie, Member member, Calendar rentDate, Calendar rentBackDate, Calendar rentBackUser, int price) {
        this.rentMovieId = rentMovieId;
        this.copyMovie = copyMovie;
        this.member = member;
        this.rentDate = rentDate;
        this.rentBackDate = rentBackDate;
        this.rentBackUser = rentBackUser;
        this.price = price;
    }

}
