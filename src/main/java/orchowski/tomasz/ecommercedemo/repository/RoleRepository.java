package orchowski.tomasz.ecommercedemo.repository;

import orchowski.tomasz.ecommercedemo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

}
