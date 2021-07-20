package orchowski.tomasz.ecommercedemo.services;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.Order;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import orchowski.tomasz.ecommercedemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }
}
