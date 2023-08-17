package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductExtendRepository {
    @Query(value = "select count(product.id) as remain " +
            "from product where provider_id = :provider and category_id = :category " +
            "order by remain desc", nativeQuery = true)
    public Integer countRemainByProviderAndCategory(@Param("provider") Long provider, @Param("category") Long category);
    @Query(value = "select p.id, p.name, p.cost, p.category, p.price, p.rate, p.remain, p.sale, p.supplier_id, p.status, p.image_id\n" +
            "from product as p\n" +
            "inner join order_detail as od on p.id = od.product_id\n" +
            "where p.category = :category\n" +
            "group by product_id\n" +
            "order by count(od.id)\n" +
            "limit 2\n"
            , nativeQuery = true)
    public List<Product> recommendProduct(@Param("category") String category);


}
