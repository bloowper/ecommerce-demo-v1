package orchowski.tomasz.ecommercedemo.controller.item;

import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemCreate;
import orchowski.tomasz.ecommercedemo.security.permision.PermissionStoreItemRead;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class itemController {

    @PermissionStoreItemCreate
    @GetMapping("/create")
    public String itemAddController() {

        return "item/create";
    }


    @PermissionStoreItemRead
    public String itemRead(Model model) {

        return "item/show";
    }
}
