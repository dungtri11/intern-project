package onlineshop.example.beeshop.model;

import lombok.*;
import onlineshop.example.beeshop.data.BillState;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[bill]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "date"
    )
    private Date date;
    @Column(
            name = "total"
    )
    private Double total;
    @OneToMany(mappedBy = "bill")
    private Set<Order> orders = new HashSet<>();
    @Enumerated(EnumType.STRING)
    @Column(
            name = "bill_state"
    )
    private BillState billState;
}
