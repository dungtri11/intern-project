package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.dto.UserCriteriaDTO;
import onlineshop.example.beeshop.model.User;
import onlineshop.example.beeshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> filterUser(@Valid UserCriteriaDTO userCriteria)  {
        logger.info(userCriteria.toString());
        return userService.findUserByCriteria(userCriteria);
    }

    @GetMapping("details/{id}")
    public User viewDetail(@PathVariable("id") Long id) {
        return userService.viewById(id);
    }

    @PostMapping("edit/{id}")
    public User editInfo(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.editInfo(user);
    }

    @PostMapping("invalid/{id}")
    public User invalidUser(@PathVariable("id") Long id) {
        return userService.invalidById(id);
    }

    @PostMapping("add")
    public User addNew(@RequestBody User user) {
        return userService.addNew(user);
    }
}
