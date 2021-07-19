package orchowski.tomasz.ecommercedemo.domain;

import lombok.*;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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
    String id;

    @ManyToOne //TODO
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    User user;

    @Enumerated(EnumType.STRING)
    OrderState orderState;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    String test_field;

}
