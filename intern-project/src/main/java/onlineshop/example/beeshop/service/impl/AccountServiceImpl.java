package onlineshop.example.beeshop.service.impl;

import onlineshop.example.beeshop.common.Role;
import onlineshop.example.beeshop.model.AccountCriteriaModel;
import onlineshop.example.beeshop.exception.DuplicateUserException;
import onlineshop.example.beeshop.exception.AccountNotFoundException;
import onlineshop.example.beeshop.entity.Account;
import onlineshop.example.beeshop.repository.AccountRepository;
import onlineshop.example.beeshop.service.AccountService;
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
        return accountRepository.findUserByCriteria(accountCriteriaModel);
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
