package onlineshop.example.beeshop.repository;

import jdk.jfr.Category;
import onlineshop.example.beeshop.common.ProductCategory;
import onlineshop.example.beeshop.entity.Customer;
import onlineshop.example.beeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select p.category\n" +
            "from beeshop.order_detail as od \n" +
            "inner join beeshop.product as p on od.product_id = p.id\n" +
            "inner join beeshop.order as o on od.order_id = o.id\n" +
            "inner join beeshop.customer as c on o.customer_id = c.id\n" +
            "where c.id = :customer_id\n" +
            "group by p.category\n" +
            "order by count(od.id) desc\n" +
            "limit 3", nativeQuery = true)
    public List<String> recommendCategory(@Param("customer_id") Long customerId);

    @Query(value = "SELECT * FROM \n" +
            "beeshop.order\n" +
            "where customer_id = :customer_id and status = 'Pending'", nativeQuery = true)
    public Optional<Order> findCart(@Param("customer_id") Long customerId);
}
