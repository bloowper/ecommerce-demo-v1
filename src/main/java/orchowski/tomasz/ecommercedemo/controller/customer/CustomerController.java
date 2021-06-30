package orchowski.tomasz.ecommercedemo.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.repository.UserRepository;
import orchowski.tomasz.ecommercedemo.security.permision.isAuthenticated;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("user")
@isAuthenticated
@RequiredArgsConstructor
public class CustomerController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session, Principal principal) {
        log.debug("principal: "+principal.getName());
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        log.debug(user.toString());
        model.addAttribute("user", user);
        return "user/profile";
    }
}
