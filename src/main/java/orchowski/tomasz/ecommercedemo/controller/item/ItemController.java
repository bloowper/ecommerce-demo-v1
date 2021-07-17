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
import orchowski.tomasz.ecommercedemo.session.ShoppingCart;
import orchowski.tomasz.ecommercedemo.utilities.PageUtilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;


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
                           @RequestParam(defaultValue = "8") Integer pageSize) {
        ///item/show?pageNo=2&pageSize=20
        var cart = ((ShoppingCart) session.getAttribute("cart"));
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
        return "item/show";
    }



    @GetMapping("/{id}/show")
    @PermissionStoreItemRead
    public String showItem(Model model, HttpSession session, @PathVariable Long id) {
        Optional<Item> byId = itemService.findById(id);
        model.addAttribute("item", byId.orElseThrow(() -> new RuntimeException("Item id " + id + " not found")));
        return "item/showProduct";
    }


    //TODO move to CartController if possible
    @PostMapping("/addToCart")
    @PermissionStoreItemRead
    public String addToCart(@RequestParam long id,
                            @RequestParam(required = false) int numberOfItems,
                            HttpSession session,
                            HttpServletRequest request,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest httpServletRequest) {
        String previousPage = PageUtilities.getPreviousPageByRequest(httpServletRequest).orElseThrow(() -> new RuntimeException("Previous page not found"));
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        var item = itemService.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        if (item.getStock() < numberOfItems) {
            redirectAttributes.addFlashAttribute("error", "Not enough items");
            return previousPage;
            //return "redirect:/item/" + id + "/show";
        }
        cart.addItem(item, numberOfItems);
        redirectAttributes.addFlashAttribute("success", "Item added to cart successfully");
        log.debug("Total size of cart : " + cart.numberOfItems());
        //return "redirect:/item/" + id + "/show";
        return previousPage;

    }


    //TODO move to CartController if possible
    @PostMapping("/editCart")
    @PermissionStoreItemRead
    public String editCart(@RequestParam @Min(0) long productID,
                           @RequestParam @Min(0) Integer numberOfItems,
                           HttpSession session,
                           Model model) {

        log.debug("Cart editing| product " + productID + " set to " + numberOfItems);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Item item = itemService.findById(productID).orElseThrow(() -> new RuntimeException("Item not found"));
        cart.editItemQuantity(item, numberOfItems);
        log.debug("Total size of cart : " + cart.numberOfItems());
        return "redirect:/user/cart";
    }


}
