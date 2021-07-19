package orchowski.tomasz.ecommercedemo.repository;

import orchowski.tomasz.ecommercedemo.domain.Order;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderState(OrderState orderState);

    List<Order> findByUser(User user);

    List<Order> findByUserAndOrderState(User user, OrderState orderState);
}
