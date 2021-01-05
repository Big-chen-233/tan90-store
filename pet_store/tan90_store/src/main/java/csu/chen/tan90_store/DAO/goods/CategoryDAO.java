package csu.chen.tan90_store.DAO.goods;

import csu.chen.tan90_store.pojo.goods.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
    Category getById(int id);
}
