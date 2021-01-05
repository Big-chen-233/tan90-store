package csu.chen.tan90_store.service.goods;

import csu.chen.tan90_store.DAO.goods.ProductDAO;
import csu.chen.tan90_store.pojo.goods.Category;
import csu.chen.tan90_store.pojo.goods.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    CategoryService categoryService;

    public List<Product> listByCategory(int id){
        Category category  = categoryService.get(id);
        return productDAO.findAllByCategory(category);
    }
    public List<Product> list(){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return productDAO.findAll(sort);
    }
    public Product getById(int pid){
        return productDAO.getOne(pid);
    }
}
