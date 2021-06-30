package orchowski.tomasz.ecommercedemo.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.Role;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.security.permision.RoleAdmin;
import orchowski.tomasz.ecommercedemo.services.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RoleAdmin
@Slf4j
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
        //log.debug(String.format("PageNumber : %d | Page size :%d", pageNo, pageSize));
        if (pageNo < 0 || pageSize<=0) {
            return "redirect:/admin/usersList?pageNo=0";
        }
        List<User> users = userService.findAll(pageNo, pageSize);
        if (users.size() == 0) {
            return "redirect:/admin/usersList?pageNo=" + (pageNo - 1);
        }
        model.addAttribute("users", users);
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("pageSize", pageSize);
        return "admin/usersList";
    }

    @GetMapping("/usersList/{id}")
    public String adminUserInfo(Model model,@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("Not found entity"));//TODO write better exception for that purpose
        model.addAttribute("user", user);
        Set<Role> roles = user.getRoles();
        model.addAttribute("roles", roles);
        return "admin/user";
    }

    @PostMapping("/usersList/{id}")
    public String adminUserLockUnlock(Model model,@PathVariable Long id,@RequestParam Boolean changeLock) {
        log.debug("Change account lock");
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("Not found entity"));//TODO write better exception
        user.setAccountNonLocked(!user.getAccountNonLocked());
        User saveUser = userService.save(user);
        //TODO
        return "redirect:/admin/usersList/" + id;
    }

}
