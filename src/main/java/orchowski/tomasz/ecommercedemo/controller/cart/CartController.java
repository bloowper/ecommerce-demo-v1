package orchowski.tomasz.ecommercedemo.controller.cart;


import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.security.permision.isAuthenticated;
import orchowski.tomasz.ecommercedemo.session.ShoopingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("user")
public class CartController {

    @isAuthenticated
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        if (session.getAttribute("cart") == null) {
            ShoopingCart cart = new ShoopingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        return "user/cart";
    }

}
