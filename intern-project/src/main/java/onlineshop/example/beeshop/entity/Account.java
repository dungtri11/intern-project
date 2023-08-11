package onlineshop.example.beeshop.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import onlineshop.example.beeshop.common.AccountStatus;
import onlineshop.example.beeshop.common.Role;


import javax.persistence.*;

@Entity
@Table(name = "[account]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true, updatable = false, length = 64, nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Column(name = "[key]", nullable = false, length = 64)
    private String identifyKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 16, updatable = false)
    private Role role;

    public Account(String username, String password, AccountStatus status, String identifyKey, Role role) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.identifyKey = identifyKey;
        this.role = role;
    }
}
