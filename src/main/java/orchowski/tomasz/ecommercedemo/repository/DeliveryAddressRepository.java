package orchowski.tomasz.ecommercedemo.repository;

import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

}
