package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select count(product.id) as remain " +
            "from product where provider_id = :provider and category_id = :category " +
            "order by remain desc", nativeQuery = true)
    public Integer countRemainByProviderAndCategory(@Param("provider") Long provider, @Param("category") Long category);
}
