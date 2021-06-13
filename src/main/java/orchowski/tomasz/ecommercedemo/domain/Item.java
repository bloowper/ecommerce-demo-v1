package orchowski.tomasz.ecommercedemo.domain;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //TODO
    // need validation for save html
    @NotNull
    @Length(min = 20, max = 1000,message = "description must be greater than 200 and smaller than 1000 characters")
    String description;



}
