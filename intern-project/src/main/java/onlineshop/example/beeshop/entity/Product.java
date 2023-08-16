package onlineshop.example.beeshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import onlineshop.example.beeshop.common.ProductCategory;
import onlineshop.example.beeshop.common.ProductStatus;
import onlineshop.example.beeshop.common.ProductRate;

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

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 32)
    private ProductCategory category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "sale")
    private Double sale;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "remain", nullable = false)
    private Integer remain;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate")
    private ProductRate productRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 16)
    private ProductStatus status;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<OrderDetail> orderDetails = new HashSet<>();
}
