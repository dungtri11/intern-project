package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountExtendRepository {
    public boolean existsByUsername(String username);


}
