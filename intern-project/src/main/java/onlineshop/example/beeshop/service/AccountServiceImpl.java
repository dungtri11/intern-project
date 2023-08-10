package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.common.Role;
import onlineshop.example.beeshop.dto.UserCriteriaDTO;
import onlineshop.example.beeshop.exception.DuplicateUserException;
import onlineshop.example.beeshop.exception.AccountNotFoundException;
import onlineshop.example.beeshop.entity.Account;
import onlineshop.example.beeshop.repository.AccountRepository;
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
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Account> findUserByCriteria(UserCriteriaDTO userCriteriaDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> userCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = userCriteriaQuery.from(Account.class);
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
            countQuery.select(criteriaBuilder.count(countQuery.from(Account.class)));
            Long count = entityManager.createQuery(countQuery).getSingleResult();

            TypedQuery<Account> userTypedQuery = entityManager.createQuery(userCriteriaQuery.select(root));
            userTypedQuery.setMaxResults(pageSize);
            userTypedQuery.setFirstResult(pageNumber - 1);

            return userTypedQuery.getResultList();

        }
        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    @Override
    public Account viewById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with id = " + id + " isn't available"));
        return account;

    }

    @Override
    public Account addNew(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new DuplicateUserException("Some unique field has been duplicated");
        }

        return accountRepository.save(account);
    }

    @Override
    public Account editInfo(Account account) {
        if (!accountRepository.existsById(account.getId())) {
            throw new AccountNotFoundException("Account with id = " + account.getId() + " isn't available");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account invalidById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with id = " + id + " isn't available")
        );
        account.setRole(Role.Invalid);
        return account;
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
