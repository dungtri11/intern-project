package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.entity.Order;

public interface OrderService {
    public Order getCart(Long customerId);

    public Order checkOutOrder(Long orderId, String address, String payment);
}
