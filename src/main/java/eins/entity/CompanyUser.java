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
@Table(name = "company_user")
public class CompanyUser extends AbstractEntity{
    private String ownership;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    private String code;

    public CompanyUser(Long id, String ownership, String fullName, String shortName, String code) {
        super(id);
        this.ownership = ownership;
        this.fullName = fullName;
        this.shortName = shortName;
        this.code = code;
    }
}
