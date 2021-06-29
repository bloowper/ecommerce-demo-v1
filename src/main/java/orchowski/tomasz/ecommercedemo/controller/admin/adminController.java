package orchowski.tomasz.ecommercedemo.controller.admin;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.security.permision.RoleAdmin;
import orchowski.tomasz.ecommercedemo.services.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RoleAdmin
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class adminController {

    private final UserService userService;

    @GetMapping("/homepage")
    public String adminHomePage(Model model) {
        //TODO
        return "admin/homepage";
    }

    @GetMapping("/usersList")
    public String adminUserList(Model model,
                                @RequestParam(defaultValue = "0") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        List<User> users = userService.findAll(pageNo, pageSize);
        model.addAttribute("users", users);
        return "admin/usersList";
    }

    @GetMapping("/usersList/{id}")
    public String adminUserInfo(Model model,@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("Not found entity"));//TODO write better exception for that purpose
        model.addAttribute("user", user);
        return "admin/user";
    }


}
