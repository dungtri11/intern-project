package onlineshop.example.beeshop.repository.impl;

import onlineshop.example.beeshop.common.Role;
import onlineshop.example.beeshop.entity.Account;
import onlineshop.example.beeshop.model.AccountCriteriaModel;
import onlineshop.example.beeshop.repository.AccountExtendRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountExtendRepositoryImpl implements AccountExtendRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Account> findUserByCriteria(AccountCriteriaModel accountCriteriaModel) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> accountCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = accountCriteriaQuery.from(Account.class);
        List<Predicate> predicates = new ArrayList<>();
        if (accountCriteriaModel.getFilterUsername() != null) {
            predicates.add(criteriaBuilder.like(root.get("username"), accountCriteriaModel.getFilterUsername() + "%"));
        }
        if (accountCriteriaModel.getFilterRole() != null) {
            predicates.add(criteriaBuilder.equal(root.get("role"), Enum.valueOf(Role.class, accountCriteriaModel.getFilterRole())));
        }
        if (accountCriteriaModel.getOrderBy() != null) {
            accountCriteriaQuery.select(root);
            if (accountCriteriaModel.getOrderBy().equals("asc")) {
                accountCriteriaQuery.orderBy(criteriaBuilder.asc(root.get("username")));
            }
            if (accountCriteriaModel.getOrderBy().equals("desc")) {
                accountCriteriaQuery.orderBy(criteriaBuilder.desc(root.get("username")));
            }
        }

        accountCriteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (accountCriteriaModel.getPage() != null) {
            int pageSize = 1;
            int pageNumber = Integer.parseInt(accountCriteriaModel.getPage());


            TypedQuery<Account> userTypedQuery = entityManager.createQuery(accountCriteriaQuery.select(root));
            userTypedQuery.setMaxResults(pageSize);
            userTypedQuery.setFirstResult(pageNumber - 1);

            return userTypedQuery.getResultList();

        }
        return entityManager.createQuery(accountCriteriaQuery).getResultList();
    }
}
