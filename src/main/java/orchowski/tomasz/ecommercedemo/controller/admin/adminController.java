package orchowski.tomasz.ecommercedemo.controller.admin;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.security.permision.RoleAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RoleAdmin
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class adminController {

    @GetMapping("/homepage")
    public String adminHomePage(Model model) {
        //TODO
        return "admin/homepage";
    }

    @GetMapping("/userlist")
    public String adminUserList(Model model) {
        //TODO
        return "";
    }
}
