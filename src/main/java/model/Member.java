package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Member {
    @Id
    private String phoneNumber;
    private String address;
    private int secretCode;

    private String creditCard;

    public Member() {}

    public Member(String phoneNumber, String address, int secretCode, String creditCard) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.address = Objects.requireNonNull(address);
        this.secretCode = Objects.requireNonNull(secretCode);
        this.creditCard = Objects.requireNonNull(creditCard);
    }
}
