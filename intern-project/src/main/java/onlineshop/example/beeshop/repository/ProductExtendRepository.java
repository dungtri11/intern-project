package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.dto.ProductBriefResponseDTO;
import onlineshop.example.beeshop.entity.Product;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductExtendRepository {

    public List<Product> findProductByCriteria(ProductCriteriaModel productCriteriaModel);
}
