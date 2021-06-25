package orchowski.tomasz.ecommercedemo.controller.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.converter.ItemCommandToItem;
import orchowski.tomasz.ecommercedemo.converter.ItemToItemCommand;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemCreate;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemRead;
import orchowski.tomasz.ecommercedemo.services.ItemService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public String itemRead(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") Integer pageNo,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        ///item/show?pageNo=2&pageSize=20

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
    public String showItem(Model model, @PathVariable Long id) {
        Optional<Item> byId = itemService.findById(id);
        model.addAttribute("item", byId.orElseThrow(() -> new RuntimeException("Item id " + id + " not found")));
        return "item/showProduct";
    }
}
