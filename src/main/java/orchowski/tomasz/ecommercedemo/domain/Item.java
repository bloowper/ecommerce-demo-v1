package orchowski.tomasz.ecommercedemo.domain;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //TODO
    // need validation for save html
    @Length(min = 20, max = 1000, message = "description must be greater than 200 and smaller than 1000 characters")
    @Lob
    String description;

    @NotNull(message = "Price can't be null")
    Double price;

    @NotNull(message = "Stock can't be null")
    @Min(value = 0, message = "Min stock value is 0")
    Integer stock;

}
