package orchowski.tomasz.ecommercedemo.command;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAddressCommand {

    private Long id;

    private String zipCode;

    private String city;

    private String street;

    private String homeNumber;

    private String phoneNumber;

}
