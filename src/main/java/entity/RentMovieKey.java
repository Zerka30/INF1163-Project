package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RentMovieKey implements Serializable {
    @Column(name = "member_id")
    private String member;
    @Column(name = "copyMovie_id")
    private int copyMovie;
}
