package onlineshop.example.beeshop.repository.impl;


import onlineshop.example.beeshop.entity.Product;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import onlineshop.example.beeshop.repository.ProductExtendRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
public class ProductExtendRepositoryImpl implements ProductExtendRepository {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Product> findProductByCriteria(ProductCriteriaModel productCriteriaModel) {
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
        return entityManager.createQuery(productCriteriaQuery).getResultList();
    }
}
