package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.UserCriteriaDTO;
import onlineshop.example.beeshop.model.User;

import java.util.List;

public interface UserService {
    public List<User> findUserByCriteria(UserCriteriaDTO userCriteriaDTO);

    public User viewById(Long id);

    public User addNew(User user);

    public User editInfo(User user);

    public User invalidById(Long id);

    public void deleteById(Long id);
}
