package orchowski.tomasz.ecommercedemo.converter;

import lombok.Synchronized;
import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import orchowski.tomasz.ecommercedemo.domain.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class ItemToItemCommand implements Converter<Item, ItemCommand> {

    @Override
    @Synchronized
    @Nullable
    public ItemCommand convert(Item item) {
        return ItemCommand.builder().id(item.getId()).name(item.getName()).description(item.getDescription()).price(item.getPrice()).stock(item.getStock()).build();
    }
}
