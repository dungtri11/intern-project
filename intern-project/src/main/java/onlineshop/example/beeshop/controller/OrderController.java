package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.entity.Order;
import onlineshop.example.beeshop.repository.OrderRepository;
import onlineshop.example.beeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/cart/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<?> showCart(@PathVariable Long customerId) {
        Order order = orderService.getCart(customerId);
        return ResponseEntity.ok(order);
    }

    @RequestMapping(value = "/checkout/{orderId}", method = RequestMethod.POST)
    public ResponseEntity<?> checkOutOrder(@PathVariable Long orderId,
                                           @RequestParam("address") String address,
                                           @RequestParam("payment") String payment) {
        Order order = orderService.checkOutOrder(orderId, address, payment);
        return ResponseEntity.ok(order);
    }
}
