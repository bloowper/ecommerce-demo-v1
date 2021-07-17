package orchowski.tomasz.ecommercedemo.session;

import com.sun.jdi.connect.Connector;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import lombok.Getter;
import lombok.Setter;
import orchowski.tomasz.ecommercedemo.domain.Item;

import java.util.*;

@Getter
@Setter
@Slf4j
public class ShoppingCart {

    String uuid;
    List<ItemCommand> items = new ArrayList<>();//TODO to remove, currently stay for compiling purpose
    Map<Item, Integer> mapItemInt = new HashMap<>();



    public void addItem(Item item, Integer numberOfItems) {
        log.debug(String.format("Adding to cart %s item id %d with quantity %d", uuid, item.getId(), numberOfItems));
        mapItemInt.computeIfPresent(item, (k, v) -> v = v+numberOfItems);
        mapItemInt.computeIfAbsent(item, item1 -> numberOfItems);

    }



    public void removeItem(Item item) {
        mapItemInt.remove(item);
    }


    public void editItemQuantity(Item item, Integer quantity) {
        if (quantity <= 0) {
            mapItemInt.remove(item);
            return;
        }
        mapItemInt.put(item, quantity);
    }

    public Integer numberOfItems() {
        return mapItemInt.values().stream().reduce(0, Integer::sum);
    }

}
