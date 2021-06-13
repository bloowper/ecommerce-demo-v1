package orchowski.tomasz.ecommercedemo.domain;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Authority  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String permission;

    @Singular
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();


}
