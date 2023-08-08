package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.ProductCriteriaDTO;
import onlineshop.example.beeshop.model.Product;
import onlineshop.example.beeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findProductByCriteria(ProductCriteriaDTO productCriteriaDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = productCriteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (productCriteriaDTO.getFilterId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), Long.parseLong(productCriteriaDTO.getFilterId())));
        }
        if (productCriteriaDTO.getFilterCategory() != null) {
            predicates.add(criteriaBuilder.equal(root.get("category_id"), Long.parseLong(productCriteriaDTO.getFilterCategory())));
        }
        if (productCriteriaDTO.getFilterProvider() != null) {
            predicates.add(criteriaBuilder.equal(root.get("provider_id"), Long.parseLong(productCriteriaDTO.getFilterProvider())));
        }
        if (productCriteriaDTO.getFilterPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), Double.parseDouble(productCriteriaDTO.getFilterPrice())));
        }
        if (productCriteriaDTO.getFilterSale() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sale"), Double.parseDouble(productCriteriaDTO.getFilterSale())));
        }
        productCriteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(productCriteriaQuery).getResultList();
    }

    @Override
    public Product viewById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product addNew(Product product) {
        if (productRepository.existsById(product.getId())) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public Product editInfo(Product product) {
        if (productRepository.existsById(product.getId())) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
