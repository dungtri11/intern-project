package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.ProductBriefResponseDTO;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import onlineshop.example.beeshop.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductBriefResponseDTO> findProductByCriteria(ProductCriteriaModel productCriteriaModel);

    public Product viewById(Long id);

    public Product addNew(Product product);

    public Product editInfo(Product product);

    public void deleteById(Long id);

    public List<ProductBriefResponseDTO> getRecommendProduct(Long userid);
}
