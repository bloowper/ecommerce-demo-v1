package orchowski.tomasz.ecommercedemo.controller.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.converter.ItemCommandToItem;
import orchowski.tomasz.ecommercedemo.converter.ItemToItemCommand;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemCreate;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemRead;
import orchowski.tomasz.ecommercedemo.services.ItemService;
import orchowski.tomasz.ecommercedemo.session.ShoopingCart;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemCommandToItem commandToItem;
    private final ItemToItemCommand itemToCommand;


    @PermissionStoreItemCreate
    @GetMapping("/create")
    public String itemAddController(Model model) {
        model.addAttribute("item", new ItemCommand());
        return "item/itemform";
    }

    @PermissionStoreItemCreate
    @PostMapping("/create/new")
    public String itemAddControllerPost(@ModelAttribute("item") @Valid ItemCommand itemCommand, BindingResult bindingResult) {
        //TODO
        // validation of ItemCommand doesnt work
        log.debug("Posting new item object");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            log.debug("Binding error");

            return "item/itemform";
        }
        Item save = itemService.save(commandToItem.convert(itemCommand));
        log.debug("New item object persisted to db \n " + save.toString());
        return "redirect:/item/" + save.getId() + "/show";
    }

    @PermissionStoreItemRead
    @GetMapping("/show")
    public String itemRead(Model model, HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "0") Integer pageNo,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        ///item/show?pageNo=2&pageSize=20
        var cart = ((ShoopingCart) session.getAttribute("cart"));
        log.debug("cart !=  null: " + (cart != null));
        if (cart != null) {
            log.debug("Cart UUID : " + cart.getUuid());
        }

        if (pageNo < 0 || pageSize <= 0) {
            return "redirect:/item/show?pageNo=0";
        }
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("pageSize", pageSize);

        List<Item> itemList = itemService.findAll(pageNo, pageSize);
        if (itemList.size() == 0) {
            return "redirect:/item/show?pageNo=" + (pageNo - 1);
        }
        model.addAttribute("items", itemList);
        System.out.println(itemList);
        return "item/show";
    }

    @GetMapping("/{id}/show")
    @PermissionStoreItemRead
    public String showItem(Model model,HttpSession session, @PathVariable Long id) {
        Optional<Item> byId = itemService.findById(id);
        model.addAttribute("item", byId.orElseThrow(() -> new RuntimeException("Item id " + id + " not found")));
        String statusInfo = null;
        if ((statusInfo= ((String) session.getAttribute("addToCartStatus"))) != null) {
            session.removeAttribute("addToCartStatus");
            model.addAttribute("addToCartStatus", statusInfo);
        }

        return "item/showProduct";
    }


    @PostMapping("/addToCart")
    @PermissionStoreItemRead
    public String addToCart(@RequestParam long id,HttpSession session,HttpServletRequest request,Model model) {
        if (session.getAttribute("cart") == null) {
            ShoopingCart cart = new ShoopingCart();
            cart.setUuid(UUID.randomUUID().toString());
            session.setAttribute("cart", cart);
        }
        Optional<Item> item = itemService.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Item not found");
        }
        ShoopingCart cart = (ShoopingCart) session.getAttribute("cart");
        cart.getItems().add(itemToCommand.convert(item.get()));
        log.debug(String.format("Adding item id %d | To cart %s",id,cart.getUuid()));
        session.setAttribute("addToCartStatus","Item added successfully");
        return "redirect:/item/" + id + "/show";
    }
}
