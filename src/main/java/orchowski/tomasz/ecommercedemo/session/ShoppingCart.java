package orchowski.tomasz.ecommercedemo.session;

import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Slf4j
public class ShoppingCart {

    String uuid;
    List<ItemCommand> items = new ArrayList<>();//TODO to remove, currently stay for compiling purpose
    Map<ItemCommand, Integer> mapItemCommandToInteger = new HashMap<>();



    public void addItem(ItemCommand itemCommand, Integer numberOfItems) {

        log.debug(String.format("Adding to cart %s item id %d with quantity %d", uuid, itemCommand.getId(), numberOfItems));

        mapItemCommandToInteger.computeIfPresent(itemCommand, (k, v) -> v = v+numberOfItems);
        mapItemCommandToInteger.computeIfAbsent(itemCommand, itemCommand1 -> numberOfItems);
    }



    public void removeItem(ItemCommand itemCommand) {
        mapItemCommandToInteger.remove(itemCommand);
    }



    public void editItemQuantity(ItemCommand itemCommand, Integer quantity) {

        if (quantity <= 0) {
            mapItemCommandToInteger.remove(mapItemCommandToInteger);
            return;
        }
        mapItemCommandToInteger.put(itemCommand, quantity);

    }



}
