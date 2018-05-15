package eins.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_to_buy")
public class ProductToBuy extends AbstractEntity{
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Invoice invoice;

    private int number;

}
