package orchowski.tomasz.ecommercedemo.session;

import orchowski.tomasz.ecommercedemo.command.ItemCommand;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ShoopingCart {
    String uuid;
    List<ItemCommand> items = new ArrayList<>();
}
