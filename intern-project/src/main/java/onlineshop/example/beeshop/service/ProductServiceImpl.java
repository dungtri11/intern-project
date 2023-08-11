package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.ProductBriefResponseDTO;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import onlineshop.example.beeshop.entity.Product;
import onlineshop.example.beeshop.repository.OrderRepository;
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
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<ProductBriefResponseDTO> findProductByCriteria(ProductCriteriaModel productCriteriaModel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = productCriteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (productCriteriaModel.getFilterId() != null) {
            predicates.add(
                    criteriaBuilder.equal(root.get("id"),
                    Long.parseLong(productCriteriaModel.getFilterId()))
            );
        }
        if (productCriteriaModel.getFilterCategory() != null) {
            predicates.add(
                    criteriaBuilder.equal(root.get("category"),
                    productCriteriaModel.getFilterCategory())
            );
        }
        if (productCriteriaModel.getFilterSupplier() != null) {
            predicates.add(
                    criteriaBuilder.equal(root.get("supplier_id"),
                    Long.parseLong(productCriteriaModel.getFilterSupplier()))
            );
        }
        if (productCriteriaModel.getFilterPrice() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(root.get("price"),
                    Double.parseDouble(productCriteriaModel.getFilterPrice()))
            );
        }
        if (productCriteriaModel.getFilterSale() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("sale"),
                    Double.parseDouble(productCriteriaModel.getFilterSale()))
            );
        }
        if (productCriteriaModel.getOrderBy() != null) {
            productCriteriaQuery.select(root);

            productCriteriaQuery.orderBy(
                    criteriaBuilder.desc(root.get("sale")),
                    criteriaBuilder.asc(root.get("price")),
                    criteriaBuilder.asc(root.get("name"))
            );
        }


        productCriteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(productCriteriaQuery).getResultList().stream().map(product -> new ProductBriefResponseDTO(product)).collect(Collectors.toList());
    }
    @Override
    public List<ProductBriefResponseDTO> getRecommendProduct(Long userid) {
        List<String> recommendCategories = orderRepository.recommendCategory(userid);
        List<ProductBriefResponseDTO> responseDTOS = new ArrayList<>();
        for (String recommendCategory : recommendCategories) {
            System.out.println(recommendCategory);
            for (Product product : productRepository.recommendProduct(recommendCategory)) {
                responseDTOS.add(new ProductBriefResponseDTO(product));
            }
        }
        return responseDTOS;
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
