package orchowski.tomasz.ecommercedemo.domain;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Scope("session")
@ToString
@EqualsAndHashCode
public class User implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    @ToString.Exclude
    private String password;

    @ToString.Exclude
    @Builder.Default
    private Boolean accountNonExpired = true;

    @ToString.Exclude
    @Builder.Default
    private Boolean accountNonLocked = true;

    @ToString.Exclude
    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @ToString.Exclude
    @Builder.Default
    private Boolean enabled = true;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @Singular("address")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<DeliveryAddress> deliveryAddressList;

    @Singular
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Order> orders;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities =
                this.roles.stream().
                map(Role::getAuthorities).
                flatMap(Set::stream).
                map(authority -> new SimpleGrantedAuthority(authority.getPermission())).
                collect(Collectors.toSet());

        roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new).forEach(authorities::add);//WE NEED IT FOR hasRole() functionality
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void addDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddressList.add(deliveryAddress);
    }

}
