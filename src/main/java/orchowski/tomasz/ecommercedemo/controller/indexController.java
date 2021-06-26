package orchowski.tomasz.ecommercedemo.controller;

import orchowski.tomasz.ecommercedemo.session.ShoopingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class indexController {

    @RequestMapping("")
    public String index(Model model, HttpSession session) {
        ShoopingCart cart = new ShoopingCart();
        cart.setUuid(UUID.randomUUID().toString());
        session.setAttribute("cart", cart);
        return "index";
    }



}
