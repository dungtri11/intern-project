package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.entity.Order;
import onlineshop.example.beeshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order getCart(Long customerId) {
        Order cart = orderRepository.findCart(customerId).orElse(new Order());
        return cart;
    }
}
