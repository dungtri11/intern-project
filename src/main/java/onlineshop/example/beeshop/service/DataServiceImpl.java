package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.ProductCriteriaDTO;
import onlineshop.example.beeshop.dto.UserCriteriaDTO;
import onlineshop.example.beeshop.model.Product;
import onlineshop.example.beeshop.data.Role;
import onlineshop.example.beeshop.model.User;
import onlineshop.example.beeshop.repository.ProductRepository;
import onlineshop.example.beeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class DataServiceImpl implements DataService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<User> findUserByCriteria(UserCriteriaDTO userCriteriaDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        if (userCriteriaDTO.getFilterUsername() != null) {
            predicates.add(criteriaBuilder.like(root.get("username"),userCriteriaDTO.getFilterUsername() + "%"));
        }
        if (userCriteriaDTO.getFilterAddress() != null) {
            predicates.add(criteriaBuilder.like(root.get("address"), "%" + userCriteriaDTO.getFilterAddress() + "%"));
        }
        if (userCriteriaDTO.getFilterEmail() != null) {
            predicates.add(criteriaBuilder.like(root.get("email"),userCriteriaDTO.getFilterEmail() + "%"));
        }
        if (userCriteriaDTO.getFilterPhone() != null) {
            predicates.add(criteriaBuilder.like(root.get("phone"),userCriteriaDTO.getFilterPhone() + "%"));
        }
        if (userCriteriaDTO.getFilterRole() != null) {
            predicates.add(criteriaBuilder.equal(root.get("role"), Enum.valueOf(Role.class, userCriteriaDTO.getFilterRole())));
        }
        if (userCriteriaDTO.getOrderBy() != null) {
            userCriteriaQuery.select(root);
            if (userCriteriaDTO.getOrderBy().equals("asc")) {
                userCriteriaQuery.orderBy(criteriaBuilder.asc(root.get("username")));
            }
            if (userCriteriaDTO.getOrderBy().equals("desc")) {
                userCriteriaQuery.orderBy(criteriaBuilder.desc(root.get("username")));
            }
        }

        userCriteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (userCriteriaDTO.getPage() != null) {
            int pageSize = 1;
            int pageNumber = Integer.parseInt(userCriteriaDTO.getPage());
            CriteriaQuery<Long> countQuery = criteriaBuilder
                    .createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(User.class)));
            Long count = entityManager.createQuery(countQuery).getSingleResult();

            TypedQuery<User> userTypedQuery = entityManager.createQuery(userCriteriaQuery.select(root));
            userTypedQuery.setMaxResults(pageSize);
            userTypedQuery.setFirstResult(pageNumber - 1);

            return userTypedQuery.getResultList();

        }
        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

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
        return entityManager.createQuery(productCriteriaQuery).getResultList();
    }




}
