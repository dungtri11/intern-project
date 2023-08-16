package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.entity.Order;
import onlineshop.example.beeshop.repository.OrderRepository;
import onlineshop.example.beeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/cart/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<?> showCart(@PathVariable Long customerId) {
        Order order = orderService.getCart(customerId);
        return ResponseEntity.ok(order);
    }
}
