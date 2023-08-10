package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findUserByUsername(String username);

    public boolean existsByUsername(String username);


}
