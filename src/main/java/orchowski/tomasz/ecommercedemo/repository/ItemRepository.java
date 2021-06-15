package orchowski.tomasz.ecommercedemo.repository;

import orchowski.tomasz.ecommercedemo.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Override
    Page<Item> findAll(Pageable pageable);
}
