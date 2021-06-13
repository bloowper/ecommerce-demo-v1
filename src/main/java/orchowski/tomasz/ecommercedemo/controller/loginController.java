package orchowski.tomasz.ecommercedemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class loginController {

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("login/?error")
    public String logginError(Model model) {
        log.debug("Loggin error");
        model.addAttribute("loginError", true);
        return "login";
    }
}
