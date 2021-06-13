package orchowski.tomasz.ecommercedemo.security;

import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class AuthenticationManager {

    public boolean customerIdMatcher(Authentication authentication, Long id){
        User user = (User) authentication.getPrincipal();
        log.debug("auth : " + user.getUsername());
        return user.getId().equals(id);
    }
}
