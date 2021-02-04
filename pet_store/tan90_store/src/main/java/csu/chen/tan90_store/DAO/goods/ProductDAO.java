package csu.chen.tan90_store.DAO.goods;

import csu.chen.tan90_store.pojo.goods.Category;
import csu.chen.tan90_store.pojo.goods.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product ,Integer> {
    void deleteById(int id);
    List<Product> findAllByCategory(Category category);
}
