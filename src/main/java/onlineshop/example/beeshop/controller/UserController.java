package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.dto.UserCriteriaDTO;
import onlineshop.example.beeshop.model.User;
import onlineshop.example.beeshop.service.DataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private DataServiceImpl dataService;
    @GetMapping
    public List<User> filterUser(@Valid UserCriteriaDTO userCriteria)  {
        logger.info(userCriteria.toString());
        return dataService.findUserByCriteria(userCriteria);
    }
}
