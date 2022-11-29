package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MemberInvoiceKey implements Serializable {
    private static final long serialVersionUID = -3607925214816263986L;
    @Column(name = "member_id")
    private String member;
    @Column(name = "invoice_id")
    private int invoice;
}
