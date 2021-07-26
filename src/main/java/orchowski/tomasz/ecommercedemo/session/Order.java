package orchowski.tomasz.ecommercedemo.session;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@Slf4j
public class Order {

    ShoppingCart cart;
    DeliveryAddress address;

}
