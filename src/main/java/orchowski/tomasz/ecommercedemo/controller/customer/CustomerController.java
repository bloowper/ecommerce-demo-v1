package orchowski.tomasz.ecommercedemo.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.DeliveryAddressCommand;
import orchowski.tomasz.ecommercedemo.converter.CommandToDeliveryAddress;
import orchowski.tomasz.ecommercedemo.converter.DeliveryAddressToCommand;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.repository.UserRepository;
import orchowski.tomasz.ecommercedemo.security.permision.RoleAdmin;
import orchowski.tomasz.ecommercedemo.security.permision.RoleCustomer;
import orchowski.tomasz.ecommercedemo.security.permision.isAuthenticated;
import orchowski.tomasz.ecommercedemo.services.DeliveryAddressService;
import orchowski.tomasz.ecommercedemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("user")
//@RoleCustomer
@Slf4j
@RequiredArgsConstructor
public class CustomerController {


    private final UserService userService;
    private final DeliveryAddressService addressService;

    private final CommandToDeliveryAddress commandToAddress;
    private final DeliveryAddressToCommand addressToCommand;


    @GetMapping("/profile")
    public String profile(Model model, HttpSession session, Principal principal) {
        log.debug("principal: " + principal.getName());
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        log.debug(user.toString());
        model.addAttribute("user", user);
        return "user/profile";
    }

    //TODO
    // IS NOT SECURED!
    @GetMapping("/profile/addAddress")
    private String addAddressForm(Model model) {
        model.addAttribute("address", new DeliveryAddressCommand());

        return "user/addressForm";
    }


    //TODO
    // IS NOT SECURED!
    @PostMapping("/profile/addAddress")
    private String addAddress(Model model,
                              Principal principal,
                              @ModelAttribute("address") @Valid DeliveryAddressCommand addressCommand,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            log.debug("Binding error");
            return "user/addressForm";
        }
        log.debug(addressCommand.toString());
        log.debug("principal: " + principal.getName());
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        DeliveryAddress address = commandToAddress.convert(addressCommand);
        address.setUser(user);
        addressService.save(address);
        return "redirect:/user/profile";
    }

    @GetMapping("/profile/editAddress")
    private String editAddress(Model model, @RequestParam long addressID) {
        log.debug(String.format("addressId %d" , addressID));
        DeliveryAddress address = addressService.findById(addressID).orElseThrow(() -> new RuntimeException("Address not found : " + addressID));
        DeliveryAddressCommand addressCommand = addressToCommand.convert(address);
        model.addAttribute("address", addressCommand);

        return "user/addressForm";
    }

    @DeleteMapping("/deleteAddress")
    public String deleteAddress(Model model, @RequestParam long addressID) {
        log.debug("Delete address " + addressID);
        DeliveryAddress address = addressService.findById(addressID).orElseThrow(() -> new RuntimeException("address not found"));
        addressService.delete(address);
        log.debug(String.format("Address %d deleted", addressID));
        return "redirect:/user/profile";
    }
}
