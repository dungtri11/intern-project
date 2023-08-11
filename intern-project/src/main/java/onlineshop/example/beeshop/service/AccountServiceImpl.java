package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.common.Role;
import onlineshop.example.beeshop.model.AccountCriteriaModel;
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
