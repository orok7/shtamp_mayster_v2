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
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "smdb_main")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_group_id")
    private ProductGroup group;

    @Column(unique = true)
    private String article;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "measurement_units")
    private MeasurementUnits measurementUnits = MeasurementUnits.PCS;

    private double price;

    private String description;

    @Column(name = "main_picture")
    private String mainPicture;

    private double rating;

    @Column(name = "number_of_ratings")
    private long numberOfRatings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();

}
