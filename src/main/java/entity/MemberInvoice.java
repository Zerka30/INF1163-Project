package entity;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member_invoice")
public class MemberInvoice {
    @EmbeddedId
    private MemberInvoiceKey memberInvoiceId;

}
