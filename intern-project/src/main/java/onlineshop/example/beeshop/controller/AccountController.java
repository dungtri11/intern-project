package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.annotation.Authorized;
import onlineshop.example.beeshop.model.AccountCriteriaModel;
import onlineshop.example.beeshop.entity.Account;
import onlineshop.example.beeshop.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:3000")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired

    private AccountService accountService;
    @GetMapping
    @Authorized(admin = false)
    public List<Account> filterUser(@Valid AccountCriteriaModel userCriteria )  {
        logger.info(userCriteria.toString());
        return accountService.findUserByCriteria(userCriteria);
    }

    @GetMapping("/details/{id}")
    public Account viewDetail(@PathVariable("id") Long id) {

        return accountService.viewById(id);
    }

    @PutMapping("/edit/{id}")
    public Account editInfo(@PathVariable("id") Long id, @RequestBody Account account) {

        return accountService.editInfo(account);
    }

    @PutMapping("/invalid/{id}")
    public Account invalidUser(@PathVariable("id") Long id) {
        return accountService.invalidById(id);
    }

    @PostMapping("/add")
    public Account addNew(@RequestBody Account account) {
        return accountService.addNew(account);
    }
}
