package orchowski.tomasz.ecommercedemo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    private User user;

    private String test;

    private String zipCode;

    private String city;

    private String street;

    private String homeNumber;

    private String phoneNumber;
}
