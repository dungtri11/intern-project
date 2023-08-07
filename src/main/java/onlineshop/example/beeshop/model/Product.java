package onlineshop.example.beeshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import onlineshop.example.beeshop.data.ProductState;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[product]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private User provider;

    @Column(
            name = "cost",
            nullable = false
    )
    private Double cost;
    @Column(
            name = "sale"
    )
    private Double sale;
    @Column(
            name = "price",
            nullable = false
    )
    private Double price;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "product_state",
            nullable = false
    )
    private ProductState productState;
    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<Order> orders = new HashSet<>();
}
