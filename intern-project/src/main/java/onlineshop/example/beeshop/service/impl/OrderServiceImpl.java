package onlineshop.example.beeshop.service.impl;

import onlineshop.example.beeshop.common.OrderStatus;
import onlineshop.example.beeshop.common.Payment;
import onlineshop.example.beeshop.entity.Order;
import onlineshop.example.beeshop.repository.CustomerRepository;
import onlineshop.example.beeshop.repository.OrderRepository;
import onlineshop.example.beeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Order getCart(Long customerId) {
        Order cart = orderRepository.findCart(customerId)
                .orElse(
                        orderRepository.save(
                                new Order(0, new Timestamp(System.currentTimeMillis()),
                                        0.0, OrderStatus.Pending,
                                        customerRepository.findById(customerId)
                                                .orElseThrow(() -> new RuntimeException("customer not found")))
                        )
        );
        return cart;
    }

    @Override
    public Order checkOutOrder(Long orderId, String address, String payment) {
        Order checkedOutOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("order not found"));
        checkedOutOrder.setAddress(address);
        checkedOutOrder.setPayment(Enum.valueOf(Payment.class, payment));
        checkedOutOrder.setDate(new Timestamp(System.currentTimeMillis()));
        checkedOutOrder.setStatus(OrderStatus.Checked_Out);
        return orderRepository.save(checkedOutOrder);
    }

}
