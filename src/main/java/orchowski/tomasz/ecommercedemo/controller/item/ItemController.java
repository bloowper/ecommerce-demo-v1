package orchowski.tomasz.ecommercedemo.controller.item;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.repository.ItemRepository;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemCreate;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemRead;
import orchowski.tomasz.ecommercedemo.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PermissionStoreItemCreate
    @GetMapping("/create")
    public String itemAddController() {

        return "item/create";
    }


    @PermissionStoreItemRead
    @GetMapping("/show")
    public String itemRead(Model model, @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        ///item/show?pageNo=2&pageSize=20
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("pageSize", pageSize);

        List<Item> itemList = itemService.findAll(pageNo, pageSize);
        model.addAttribute("items", itemList);
        System.out.println(itemList);
        return "item/show";
    }

    @GetMapping("/show/{id}")
    public String showItem(Model model) {

        return "item/showProduct";
    }
}
