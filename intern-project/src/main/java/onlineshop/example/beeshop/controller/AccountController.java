package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.annotation.Authority;
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
@CrossOrigin("http://localhost:3000")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public List<Account> filterUser(@Valid AccountCriteriaModel userCriteria )  {
        logger.info(userCriteria.toString());
        return accountService.findUserByCriteria(userCriteria);
    }
    @Authority(admin = true)
    @RequestMapping(value = "/account/details/{id}", method = RequestMethod.GET)
    public Account viewDetail(@PathVariable("id") Long id) {
        return accountService.viewById(id);
    }

    @RequestMapping(value = "/account/edit/{id}", method = RequestMethod.PUT)
    public Account editInfo(@PathVariable("id") Long id, @RequestBody Account account) {

        return accountService.editInfo(account);
    }

    @RequestMapping(value = "/account/invalid/{id}", method = RequestMethod.PUT)
    public Account invalidUser(@PathVariable("id") Long id) {
        return accountService.invalidById(id);
    }

    @RequestMapping(value = "/account/add", method = RequestMethod.POST)
    public Account addNew(@RequestBody Account account) {
        return accountService.addNew(account);
    }
}
