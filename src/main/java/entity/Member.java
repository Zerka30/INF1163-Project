package entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "member")
public final class Member {
    @Id
    private String phoneNumber;
    private String address;
    private String creditCard;
    private String expireDate;
    private String secretCode;
    private int secretPassword;



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

    public Member(String phoneNumber, String address, String creditCard, String expireDate, String secretCode) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.address = Objects.requireNonNull(address);
        this.creditCard = Objects.requireNonNull(creditCard);
        this.expireDate = Objects.requireNonNull(expireDate);
        this.secretCode = Objects.requireNonNull(secretCode);
        // Generate a random secret password of 6 digits
        this.secretPassword = Objects.requireNonNull((int) (Math.random() * 900000) + 100000);
    }

    @Override
    public String toString() {
        return "Numéro de téléphone : " + this.phoneNumber + "\n" +
                "Adresse : " + this.address + "\n" +
                "Carte de crédit : " + this.creditCard + "\n" +
                "Date d'expiration : " + this.expireDate + "\n" +
                "Code secret : " + this.secretCode + "\n" +
                "Mot de passe secret : " + this.secretPassword + "\n";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSecretCode() {
        return secretPassword;
    }
}