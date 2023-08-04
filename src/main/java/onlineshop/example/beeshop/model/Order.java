package onlineshop.example.beeshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "[order]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;
    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;
    @Column(
            name = "subtotal",
            nullable = false
    )
    private Double subtotal;

}
