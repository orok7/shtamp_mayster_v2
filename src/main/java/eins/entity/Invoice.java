package eins.entity;

import eins.entity.enums.InvoiceStatus;
import eins.entity.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User buyer;

    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.MERGE)
    private List<ProductToBuy> products = new ArrayList<>();

    private double sum;

    private String note;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceStatus status = InvoiceStatus.DRAFT;

    private double payed;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type")
    private PaymentType paymentType = PaymentType.CASH_ON_DELIVERY;
}
