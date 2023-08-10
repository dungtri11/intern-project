package onlineshop.example.beeshop.entity;

import lombok.*;
import onlineshop.example.beeshop.common.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
    private long id;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany(mappedBy = "orders")
    private Set<Voucher> vouchers = new HashSet<>();
}
