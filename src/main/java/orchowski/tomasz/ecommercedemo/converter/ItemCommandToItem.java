package orchowski.tomasz.ecommercedemo.converter;


import lombok.Synchronized;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.domain.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ItemCommandToItem implements Converter<ItemCommand, Item> {
    @Override
    @Nullable
    @Synchronized
    public Item convert(ItemCommand itemCommand) {
        Item item = Item.builder().name(itemCommand.getName()).description(itemCommand.getDescription()).price(itemCommand.getPrice()).stock(itemCommand.getStock()).build();
        return item;
    }


}
