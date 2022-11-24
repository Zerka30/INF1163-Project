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
}
