package orchowski.tomasz.ecommercedemo.domain;

import lombok.*;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne //TODO
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    User user;

    @Enumerated(EnumType.STRING)
    OrderState orderState;

    String test_field;

}
