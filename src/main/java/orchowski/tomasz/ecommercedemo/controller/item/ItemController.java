package orchowski.tomasz.ecommercedemo.controller.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.converter.ItemCommandToItem;
import orchowski.tomasz.ecommercedemo.converter.ItemToItemCommand;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemCreate;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemRead;
import orchowski.tomasz.ecommercedemo.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

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
    @PostMapping("/create")
    public String itemAddControllerPost(@Valid @ModelAttribute("item") ItemCommand itemCommand) {
        log.debug("Posting new item object");

        return "";
    }

    @PermissionStoreItemRead
    @GetMapping("/show")
    public String itemRead(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") Integer pageNo,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        ///item/show?pageNo=2&pageSize=20
        Principal userPrincipal = request.getUserPrincipal();
        System.out.println(userPrincipal.getName());


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

    @GetMapping("/show/{id}")
    public String showItem(Model model,@PathVariable Long id) {

        return "item/showProduct";
    }
}
