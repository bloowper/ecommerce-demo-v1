package orchowski.tomasz.ecommercedemo.repository;

import orchowski.tomasz.ecommercedemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);



}
