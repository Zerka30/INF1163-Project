package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Embeddable
public class RentMovieKey implements Serializable {
    @Column(name = "member_phonenumber")
    private String memberId;
    @Column(name = "copyMovie_id")
    private int copyMovieId;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "rent_date")
    private Calendar rentDate;
    public RentMovieKey() {}

    public RentMovieKey(String memberId, int copyMovieId, Calendar rentDate) {
        this.memberId = memberId;
        this.copyMovieId = copyMovieId;
        this.rentDate = rentDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getCopyMovieId() {
        return copyMovieId;
    }

    public Calendar getRentDate() {
        return rentDate;
    }

}
