package orchowski.tomasz.ecommercedemo.command;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressCommand {

    private Long id;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String homeNumber;

    @NotBlank
    private String phoneNumber;

}
