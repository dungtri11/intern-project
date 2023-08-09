package onlineshop.example.beeshop.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import onlineshop.example.beeshop.data.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "[user]")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(
            name = "username",
            unique = true,
            updatable = false,
            length = 64,
            nullable = false
    )
    private String username;

    @Column(
            name = "password",
            nullable = false,
            length = 64
    )
    private String password;

    @Column(
            name = "email",
            length = 64,
            unique = true
    )
    private String email;
    @Column(
            name = "phone",
            length = 16,
            unique = true,
            nullable = false
    )
    private String phone;
    @Column(
            name = "address",
            length = 128
    )
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            length = 16,
            updatable = false
    )
    private Role role;

    @OneToMany(mappedBy = "provider")
    @JsonBackReference
    private Set<Product> products = new HashSet<>();

    public User(String username, String password, String email, String phone, String address, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = List.of((GrantedAuthority) () -> role.toString());
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
