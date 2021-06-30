package orchowski.tomasz.ecommercedemo.services;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.Role;
import orchowski.tomasz.ecommercedemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    /**
     * @param role ROLE_CUSTOMER || ROLE_ADMIN
     * @return instance of Role
     */
    public Optional<Role> findByName(String role) {
        return roleRepository.findByName(role);
    }
}
