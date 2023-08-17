package onlineshop.example.beeshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import onlineshop.example.beeshop.common.OrderStatus;
import onlineshop.example.beeshop.common.Payment;

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
    @JsonBackReference
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "total", nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 16)
    private OrderStatus status;

    @Column(name = "address", length = 128)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment", length = 16)
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany(mappedBy = "orders")
    @JsonBackReference
    private Set<Voucher> vouchers = new HashSet<>();


    public Order(long id, Timestamp date, Double total, OrderStatus status, Customer customer) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.status = status;
        this.customer = customer;
    }
}
