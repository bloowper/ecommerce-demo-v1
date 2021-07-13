package orchowski.tomasz.ecommercedemo.controller;

import orchowski.tomasz.ecommercedemo.session.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class indexController {

    @RequestMapping("")
    public String index(Model model, HttpSession session) {
        //TODO setting  cart session attribute should be done fore every user not only for that who open home page
        if (session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        return "index";
    }

    //for testing purpus
    @GetMapping("home")
    public String home() {

        return "index";
    }

}
