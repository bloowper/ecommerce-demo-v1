package orchowski.tomasz.ecommercedemo.controller.customer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.converter.ItemToItemCommand;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.security.permision.isAuthenticated;
import orchowski.tomasz.ecommercedemo.services.ItemService;
import orchowski.tomasz.ecommercedemo.session.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.UUID;


@RequestMapping("user")
@Controller
@Slf4j
@RequiredArgsConstructor
public class CartController {

    private final ItemService itemService;

    private final ItemToItemCommand toItemCommand;

    @isAuthenticated
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model,Long id) {
        if (session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
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
            ShoppingCart cart = new ShoppingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            Item item = itemService.findById(id).orElseThrow(() -> new RuntimeException("Item with given id not found :" + id));
            cart.removeItem(item);
        }
        session.setAttribute("cartRemoveItem", true);
        return "redirect:/user/cart";
    }
}
