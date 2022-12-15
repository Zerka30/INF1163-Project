package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RentMovieKey implements Serializable {
    @Column(name = "member_phonenumber")
    private String memberId;
    @Column(name = "copyMovie_id")
    private int copyMovieId;

    public RentMovieKey() {}

    public RentMovieKey(String memberId, int copyMovieId) {
        this.memberId = memberId;
        this.copyMovieId = copyMovieId;
    }
}
