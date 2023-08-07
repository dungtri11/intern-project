package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.dto.ProductRemainGroupDTO;
import onlineshop.example.beeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
