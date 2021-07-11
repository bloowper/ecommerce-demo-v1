package orchowski.tomasz.ecommercedemo.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.DeliveryAddressCommand;
import orchowski.tomasz.ecommercedemo.command.DeliveryAddressListCommand;
import orchowski.tomasz.ecommercedemo.converter.CommandToDeliveryAddress;
import orchowski.tomasz.ecommercedemo.converter.DeliveryAddressToCommand;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import orchowski.tomasz.ecommercedemo.domain.Role;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.security.permision.RoleAdmin;
import orchowski.tomasz.ecommercedemo.services.DeliveryAddressService;
import orchowski.tomasz.ecommercedemo.services.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RoleAdmin
@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class adminController {

    private final UserService userService;
    private final DeliveryAddressService addressService;

    private final DeliveryAddressToCommand addressToCommand;
    private final CommandToDeliveryAddress commandToAddress;



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
    public String adminUserInfo(@PathVariable Long id,
                                Model model,
                                HttpSession session
    ) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("Not found entity"));//TODO write better exception for that purpose
        model.addAttribute("user", user);
        Set<Role> roles = user.getRoles();
        model.addAttribute("roles", roles);

        if (session.getAttribute("deleteInfo")!=null) {
            model.addAttribute("deleteInfo", session.getAttribute("deleteInfo"));
            session.removeAttribute("deleteInfo");
        }
        return "admin/user";
    }



    /**
     * Deleting address for user
     */
    @DeleteMapping(value = "/usersList/{userId}/deleteAddress")
    public String deleteAddress(@PathVariable int userId,
                                @RequestParam long addressID,
                                HttpSession session) {
        // For working http DELETE we need to provide @Bean hiddenHttpMethodFilter
        log.debug(String.format("For user %d deleting address %s", userId, addressID));
        DeliveryAddress address = addressService.findById(addressID).orElseThrow(() -> new RuntimeException("Address not found"));
        addressService.delete(address);
        session.setAttribute("deleteInfo", "Address deleted successfully");
        return "redirect:/admin/usersList/{userId}";
    }



    @GetMapping(value = "/usersList/{userId}/addAddress")
    public String addAddress(Model model,
                             @PathVariable long userId) {

        model.addAttribute("address", new DeliveryAddressCommand());
        var userById = userService.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User %d not found",userId)));
        model.addAttribute("username",userById.getUsername());
        model.addAttribute("userId", userId);
        return "admin/addressForm";
    }

    @GetMapping(value = "/usersList/{userId}/editAddress")
    public String editAddress(Model model,
                              @PathVariable long userId,
                              @RequestParam long addressID) {

        log.debug(String.format("userID %d addressId %d", userId, addressID));
        DeliveryAddress address = addressService.findById(addressID).orElseThrow(() -> new RuntimeException("Address not found : " + addressID));
        DeliveryAddressCommand addressCommand = addressToCommand.convert(address);
        model.addAttribute("address", addressCommand);
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User id %d not found", userId)));
        model.addAttribute("username",user.getUsername());
        model.addAttribute("userId", userId);

        return "admin/addressForm";
    }




    @PostMapping("/usersList/{userId}/addAddress")
    public String addAddressPost(@PathVariable long userId,
                                 @ModelAttribute("address") @Valid DeliveryAddressCommand addressCommand,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            log.debug("Binding error");
            return "admin/addressForm";
        }
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User id %d not found", userId)));
        log.debug("Posting new address for user id: " + userId+" username: "+user.getUsername());
        DeliveryAddress address = commandToAddress.convert(addressCommand);
        log.debug(address.toString());
        address.setUser(user);
        address = addressService.save(address);
        log.debug(address.toString());

        return "redirect:/admin/usersList/{userId}";
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
