package orchowski.tomasz.ecommercedemo.services;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import orchowski.tomasz.ecommercedemo.repository.DeliveryAddressRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressRepository addressRepository;

    public DeliveryAddress save(DeliveryAddress deliveryAddress) {
        return  addressRepository.save(deliveryAddress);
    }
}
