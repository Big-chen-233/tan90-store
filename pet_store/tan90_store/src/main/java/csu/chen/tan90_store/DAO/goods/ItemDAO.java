package csu.chen.tan90_store.DAO.goods;

import csu.chen.tan90_store.pojo.goods.Item;
import csu.chen.tan90_store.pojo.goods.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item ,Integer> {
    List<Item> getAllByProduct(Product product);
}
