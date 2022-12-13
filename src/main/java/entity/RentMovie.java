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

    public void setPrice(int price) {
        this.price = price;
    }
    public void setrentbackdate() {
        this.rentBackDate = Calendar.getInstance();
    }

    @Override
    public String toString() {
        return "RentMovie{" +
                "rentMovieId=" + rentMovieId +
                ", copyMovie=" + copyMovie +
                ", member=" + member +
                ", rentDate=" + rentDate +
                ", rentBackDate=" + rentBackDate +
                ", rentBackUser=" + rentBackUser +
                ", price=" + price +
                '}';
    }
}
