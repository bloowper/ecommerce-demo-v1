package orchowski.tomasz.ecommercedemo.services;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.Order;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import orchowski.tomasz.ecommercedemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findByOrderState(OrderState orderState) {
        return orderRepository.findByOrderState(orderState);
    }

    public List<Order> findByUser(User user){
        return orderRepository.findByUser(user);
    }

    public List<Order> findByUserAndOrderState(User user, OrderState orderState) {
        return orderRepository.findByUserAndOrderState(user, orderState);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }
}