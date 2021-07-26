package orchowski.tomasz.ecommercedemo.controller.customer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.converter.ItemToItemCommand;
import orchowski.tomasz.ecommercedemo.domain.DeliveryAddress;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.domain.Order;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.domain.enums.OrderState;
import orchowski.tomasz.ecommercedemo.repository.DeliveryAddressRepository;
import orchowski.tomasz.ecommercedemo.security.permision.isAuthenticated;
import orchowski.tomasz.ecommercedemo.services.DeliveryAddressService;
import orchowski.tomasz.ecommercedemo.services.ItemService;
import orchowski.tomasz.ecommercedemo.services.OrderService;
import orchowski.tomasz.ecommercedemo.services.UserService;
import orchowski.tomasz.ecommercedemo.session.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;




@RequestMapping("user")
@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("order")
public class CartController {

    private final ItemService itemService;
    private final UserService userService;
    private final DeliveryAddressService deliveryAddressService;
    private final OrderService orderService;

    private final ItemToItemCommand toItemCommand;

    @ModelAttribute("order")
    public Order setOrder() {
        return new Order();
    }

    @isAuthenticated
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model, Long id) {
        if (session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        return "user/cart";
    }

    @isAuthenticated
    @PostMapping("/cart/removeItem")
    public String cartRemoveItem(HttpSession session,
                                 Model model,
                                 @RequestParam long id,
                                 RedirectAttributes redirectAttributes
    ) {
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
        redirectAttributes.addFlashAttribute("success", "Remove item successfully");
        return "redirect:/user/cart";
    }

    @isAuthenticated
    @GetMapping("/buy/addressSelecting")
    public String addressSelecting(Model model,
                                   Principal principal,
                                   HttpSession session) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            return "redirect:/";
        }
        List<DeliveryAddress> deliveryAddressList = user.getDeliveryAddressList();
        model.addAttribute("deliveryAddressList", deliveryAddressList);
        return "user/buyAddressSelecting";
    }


    @isAuthenticated
    @PostMapping("/buy/addressSelecting")
    public String addressSelectingPost(Model model,
                                       Principal principal,
                                       HttpSession session,
                                       @RequestParam Long id,
                                       Order order) {
        //i guss that exist better solution for choosing one element from list by checkbox, but
        // i have no idea how to do that
        log.debug("chosen id: " + id);
        log.debug("order == null >>>"+(order==null));
        DeliveryAddress address = deliveryAddressService.findById(id).orElseThrow(() -> new RuntimeException("Delivery address with given id not found!"));
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (order == null) {
            throw new RuntimeException("Session attribute order not injected");
        }
        if (cart == null) {
            throw new RuntimeException("Cart HttpSession attribute not found");
        }
        String userName = principal.getName();
        User user = userService.findByUsername(userName).orElseThrow(() -> new RuntimeException(String.format("User %s not found", userName)));

        order.setUser(user);
        order.setOrderState(OrderState.PLACED);
        order.setDeliveryAddress(address);
        order.setItems(cart.getMapItemInt());

        return "redirect:/user/buy/orderSummary";
    }

    @isAuthenticated
    @GetMapping("/buy/orderSummary")
    public String orderSummary(Model model,
                               Principal principal,
                               HttpSession session,
                               Order order) {

        return "user/orderSummary";
    }

    @isAuthenticated
    @GetMapping("/buy/orderAccept")
    public String orderAccept(Model model,
                               Principal principal,
                               HttpSession session,
                               Order order) {
        order.setId(null);
        Order saveOrder = orderService.save(order);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        //TODO HIGH PRIORITY transactional layer in service
        // IM SURE in 99% that current implementation would make many problems
        for (Map.Entry<Item, Integer> itemIntegerEntry : cart.getMapItemInt().entrySet()) {
            Item key = itemIntegerEntry.getKey();
            Integer value = itemIntegerEntry.getValue();
            Item item = itemService.findById(key.getId()).orElseThrow(() -> new RuntimeException("Item not exist"));
            item.setStock(item.getStock() - value);
            itemService.save(item);
        }
        cart.setMapItemInt(new HashMap<>());
        //TODO change item quantity in Item table
        return "redirect:/user/order/" + saveOrder.getId();
    }

}
