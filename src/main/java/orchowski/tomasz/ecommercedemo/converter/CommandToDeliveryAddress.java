package orchowski.tomasz.ecommercedemo.converter;

import lombok.Synchronized;
import orchowski.tomasz.ecommercedemo.command.DeliveryAddressCommand;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToDeliveryAddress implements Converter<DeliveryAddressCommand,DeliveryAddress> {

    @Override
    @Nullable
    @Synchronized
    public DeliveryAddress convert(DeliveryAddressCommand source) {
        //TODO merge that ifs
        if (source.getId() != null) {
            return DeliveryAddress.builder()
                    .id(source.getId())
                    .zipCode(source.getZipCode())
                    .city(source.getCity())
                    .street(source.getStreet())
                    .homeNumber(source.getHomeNumber())
                    .phoneNumber(source.getPhoneNumber())
                    .build();
        }
        return DeliveryAddress.builder()
                .zipCode(source.getZipCode())
                .city(source.getCity())
                .street(source.getStreet())
                .homeNumber(source.getHomeNumber())
                .phoneNumber(source.getPhoneNumber())
                .build();

    }

}
