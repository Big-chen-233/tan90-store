package csu.chen.tan90_store.service.goods;

import csu.chen.tan90_store.DAO.goods.CategoryDAO;
import csu.chen.tan90_store.pojo.goods.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;
    Category get(int id){
        return categoryDAO.getById(id);
    }
}
