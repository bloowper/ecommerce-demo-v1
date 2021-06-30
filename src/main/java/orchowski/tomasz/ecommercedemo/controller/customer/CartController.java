package orchowski.tomasz.ecommercedemo.controller.customer;


import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.security.permision.isAuthenticated;
import orchowski.tomasz.ecommercedemo.session.ShoopingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("user")
public class CartController {

    @isAuthenticated
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model,Long id) {
        if (session.getAttribute("cart") == null) {
            ShoopingCart cart = new ShoopingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        if (session.getAttribute("cartRemoveItem") != null) {
            session.removeAttribute("cartRemoveItem");
            model.addAttribute("cartRemoveItem", true);
        }
        return "user/cart";
    }

    @isAuthenticated
    @PostMapping("/cart/removeItem")
    public String cartRemoveItem(HttpSession session, Model model, @RequestParam long id) {
        if (session.getAttribute("cart") == null) {
            ShoopingCart cart = new ShoopingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        ShoopingCart cart = (ShoopingCart) session.getAttribute("cart");
        if (cart != null) {
            cart.getItems().removeIf(itemCommand -> itemCommand.getId() == id);
        }
        session.setAttribute("cartRemoveItem", true);
        return "redirect:/user/cart";
    }
}
