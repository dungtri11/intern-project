package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.model.AccountCriteriaModel;
import onlineshop.example.beeshop.entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findUserByCriteria(AccountCriteriaModel accountCriteriaModel);

    public Account viewById(Long id);

    public Account addNew(Account account);

    public Account editInfo(Account account);

    public Account invalidById(Long id);

    public void deleteById(Long id);
}
