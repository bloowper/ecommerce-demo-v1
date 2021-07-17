package orchowski.tomasz.ecommercedemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.RegisterCommand;
import orchowski.tomasz.ecommercedemo.domain.Role;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.repository.RoleRepository;
import orchowski.tomasz.ecommercedemo.services.UserService;
import orchowski.tomasz.ecommercedemo.session.ShoppingCart;
import org.dom4j.rule.Mode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginRegisterController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error, HttpSession session) {

        if (error != null) {
            model.addAttribute("loginError", true);
        }
        if (session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model,HttpSession session) {
        model.addAttribute("registerCommand", new RegisterCommand());

        if (session.getAttribute("emailOccupied") != null) {
            session.removeAttribute("emailOccupied");
            model.addAttribute("emailOccupied", true);
        }
        if (session.getAttribute("usernameOccupied") != null) {
            session.removeAttribute("usernameOccupied");
            model.addAttribute("usernameOccupied", true);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, HttpSession session, @ModelAttribute RegisterCommand registerCommand) {
        log.debug(registerCommand.toString());
        // FIXME: 30.06.2021
        Optional<User> byUsername = userService.findByUsername(registerCommand.getUsername());
        Optional<User> byEmail = userService.findByEmail(registerCommand.getEmail());
        if (byUsername.isPresent() || byEmail.isPresent()) {
            if (byEmail.isPresent()) {
                session.setAttribute("emailOccupied", true);
                log.debug(byEmail.get().toString());

            }
            if (byUsername.isPresent()) {
                session.setAttribute("usernameOccupied", true);
                log.debug(byUsername.get().toString());
            }
            return "redirect:/register";
        }
        Role role_customer = roleRepository.findByName("ROLE_CUSTOMER").orElseThrow(() -> new RuntimeException("Not found ROLE_?"));//TODO implement better exception
        User user = User.builder().
                role(role_customer).
                username(registerCommand.getUsername()).
                password(passwordEncoder.encode(registerCommand.getPassword())).
                email(registerCommand.getEmail()).
                build();
        User save = userService.save(user);
        log.debug(save.toString());

        if (session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }

        return "redirect:/login";
    }
}
