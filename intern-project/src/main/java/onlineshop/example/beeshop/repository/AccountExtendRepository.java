package onlineshop.example.beeshop.repository;

import onlineshop.example.beeshop.entity.Account;
import onlineshop.example.beeshop.model.AccountCriteriaModel;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountExtendRepository {

    public List<Account> findUserByCriteria(AccountCriteriaModel accountCriteriaModel);
}
