package orchowski.tomasz.ecommercedemo.command;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Lob;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ItemCommand {

    private Long id;

    @Lob
    @Length(min = 4, max = 100, message = "You must provide name of product longer than 10 characters shorten than 100")
    String name;

    @Size(min = 20, max = 1000,message = "Description must contains at least 20 characters and less than 1000")
    @NotBlank
    private String description;

    @Min(value = 0, message = "Price have to be greater than 0")
    @NotNull
    private Double price;

    @EqualsAndHashCode.Exclude
    @Min(value = 0, message = "Stock have to be at least 0")
    @NotNull
    private Integer stock;

}
