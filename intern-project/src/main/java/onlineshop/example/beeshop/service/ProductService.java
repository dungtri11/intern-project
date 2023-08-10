package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.ProductCriteriaDTO;
import onlineshop.example.beeshop.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findProductByCriteria(ProductCriteriaDTO productCriteriaDTO);

    public Product viewById(Long id);

    public Product addNew(Product product);

    public Product editInfo(Product product);

    public void deleteById(Long id);
}
