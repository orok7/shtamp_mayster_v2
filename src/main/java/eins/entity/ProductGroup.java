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
@Table(name = "product_group")
public class ProductGroup extends AbstractEntity {
    private String name;

    public ProductGroup(Long id, String name) {
        super(id);
        this.name = name;
    }
}
