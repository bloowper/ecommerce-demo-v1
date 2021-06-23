package orchowski.tomasz.ecommercedemo.services;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.domain.Item;
import orchowski.tomasz.ecommercedemo.repository.ItemRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     *  @see ItemService#findAll(Integer, Integer, String)
     */
    public List<Item> findAll(Integer page, Integer size) {
        return findAll(page, size, "id");
    }

    /**
     * @param page page number
     * @param size number of domain object per page
     * @param sort name of domain object field to sort by
     * @return domain object list for related page
     *
     */
    public List<Item> findAll(Integer page, Integer size,String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        Page<Item> all = itemRepository.findAll(pageRequest);
        return all.getContent();
    }

    public Item save(Item item) {
        Item save = itemRepository.save(item);
        return save;
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }
}
