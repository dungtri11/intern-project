package onlineshop.example.beeshop.model;

import lombok.*;
import onlineshop.example.beeshop.data.Manufacturer;
import onlineshop.example.beeshop.data.Rate;
import onlineshop.example.beeshop.data.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[category]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            length = 64
    )
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "manufacturer",
            nullable = false,
            length = 64
    )
    private Manufacturer manufacturer;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false,
            length = 16
    )
    private Type type;
    @Column(
            name = "stats",
            length = 255
    )
    private String stats;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "rate",
            nullable = false,
            length = 16
    )
    private Rate rate;
    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();
}
