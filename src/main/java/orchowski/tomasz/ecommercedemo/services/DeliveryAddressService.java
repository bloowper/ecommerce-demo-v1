package orchowski.tomasz.ecommercedemo.services;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import orchowski.tomasz.ecommercedemo.repository.DeliveryAddressRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressRepository addressRepository;

    public DeliveryAddress save(DeliveryAddress deliveryAddress) {
        return  addressRepository.save(deliveryAddress);
    }

    public Optional<DeliveryAddress> findById(Long id) {
        return addressRepository.findById(id);
    }

    public void delete(DeliveryAddress deliveryAddress) {
        addressRepository.delete(deliveryAddress);
    }
}
