package onlineshop.example.beeshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[supplier]")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            name = "address",
            length = 256
    )
    private String address;
    @Column(
            name = "phone",
            length = 16
    )
    private String phone;
    @Column(
            name = "email",
            length = 64
    )
    private String email;
    @Column(
            name = "facebook",
            length = 256
    )
    private String facebook;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "supplier")
    @JsonBackReference
    private Set<Product> products = new HashSet<>();
}
