package orchowski.tomasz.ecommercedemo.domain;

import lombok.*;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne //TODO not sure that it works as it should
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

    //TODO check if it is correct
    // and simplify names of columns ect
    @Singular
    @ElementCollection
    // @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    Map<Item, Integer> items;

    @ManyToOne
    DeliveryAddress deliveryAddress;

    public Order addToMap(Item key, Integer value) {
        items.put(key, value);
        return this;
    }

    @Transient
    public Double getOrderCost() {
        Double reduce = items.entrySet().stream().map(entry -> {
            return entry.getKey().getPrice() * entry.getValue();
        }).reduce(0., Double::sum);
        return reduce;
    }

    @Transient
    public Integer getNumberOfItems() {
        return items.values().stream().reduce(0, Integer::sum);
    }
}
