package orchowski.tomasz.ecommercedemo.converter;


import lombok.Synchronized;
import orchowski.tomasz.ecommercedemo.command.DeliveryAddressCommand;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressToCommand implements Converter<DeliveryAddress, DeliveryAddressCommand> {

    @Override
    @Nullable
    @Synchronized
    public DeliveryAddressCommand convert(DeliveryAddress source) {
       return DeliveryAddressCommand.builder().
               id(source.getId()).
               zipCode(source.getZipCode()).
               city(source.getCity()).
               street(source.getStreet()).
               homeNumber(source.getHomeNumber()).
               phoneNumber(source.getPhoneNumber()).
               build();
    }

}
