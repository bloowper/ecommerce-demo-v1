package orchowski.tomasz.ecommercedemo.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.Authority;
import orchowski.tomasz.ecommercedemo.domain.Role;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.repository.AuthorityRepository;
import orchowski.tomasz.ecommercedemo.repository.RoleRepository;
import orchowski.tomasz.ecommercedemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Slf4j
@RequiredArgsConstructor
@Component
public class defaultLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @Override

    public void run(String... args) throws Exception {
        loadAccount();
    }

    private void loadAccount() {
        var storeItemCreate = authorityRepository.save(Authority.builder().permission("store.item.create").build());
        var storeItemRead = authorityRepository.save(Authority.builder().permission("store.item.read").build());
        var storeItemUpdate = authorityRepository.save(Authority.builder().permission("store.item.update").build());
        var storeItemDelete = authorityRepository.save(Authority.builder().permission("store.item.delete").build());


        var admin = roleRepository.save(Role.builder().authority(storeItemCreate).authority(storeItemDelete).authority(storeItemUpdate).authority(storeItemDelete).name("ADMIN").build());
        var customer = roleRepository.save(Role.builder().authority(storeItemCreate).name("CUSTOMER").build());

        userRepository.save(User.builder().
                role(admin).
                username("admin").
                password(passwordEncoder.encode("admin")).
                email("admin@admin.com").
                role(admin).
                build()
        );


    }


}
