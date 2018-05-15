package eins.entity;

import eins.entity.enums.MeasurementUnits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends AbstractEntity{
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private ProductGroup group;

    @Column(unique = true)
    private String article;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private MeasurementUnits measurementUnits = MeasurementUnits.PCS;

    private double price;

    private String description;

    @Column(name = "main_picture")
    private String mainPicture;

    private double rating;

    @Column(name = "number_of_ratings")
    private int numberOfRatings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();

}
