package entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "member")
public final class Member implements MyTable {
    @Id
    private String phoneNumber;
    private String address;
    private int secretCode;

    private String creditCard;

    @OneToMany(
            mappedBy = "copyMovie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RentMovie> movies;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "member_invoice",
            joinColumns = { @JoinColumn(name = "member_id") },
            inverseJoinColumns = { @JoinColumn(name = "invoice_id") }
    )
    Set<Invoice> invoices;

    public Member() {}

    public Member(String phoneNumber, String address, int secretCode, String creditCard) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.address = Objects.requireNonNull(address);
        this.secretCode = Objects.requireNonNull(secretCode);
        this.creditCard = Objects.requireNonNull(creditCard);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSecretCode() {
        return secretCode;
    }
}
