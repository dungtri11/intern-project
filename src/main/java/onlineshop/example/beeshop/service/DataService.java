package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.dto.ProductCriteriaDTO;
import onlineshop.example.beeshop.dto.ProductRemainGroupDTO;
import onlineshop.example.beeshop.dto.UserCriteriaDTO;
import onlineshop.example.beeshop.model.Product;
import onlineshop.example.beeshop.model.User;

import java.util.List;

public interface DataService {
    public List<User> findUserByCriteria(UserCriteriaDTO userCriteriaDTO);

    public User save(User user);

    public void deleteById(Long id);

    public List<Product> findProductByCriteria(ProductCriteriaDTO productCriteriaDTO);


}
