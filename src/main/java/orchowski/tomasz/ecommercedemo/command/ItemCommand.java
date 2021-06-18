package orchowski.tomasz.ecommercedemo.command;

import lombok.*;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCommand {

    private Long id;
    @NotBlank
    @Size(min = 20, max = 1000,message = "Description must contains at least 20 characters and less than 1000")
    private String description;
    @Min(value = 0, message = "Price have to be greater than 0")
    private Double price;
    @Min(value = 0, message = "Stock have to be at least 0")
    private Integer stock;

}
