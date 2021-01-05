package csu.chen.tan90_store.service.goods;

import csu.chen.tan90_store.DAO.goods.ItemDAO;
import csu.chen.tan90_store.pojo.goods.Category;
import csu.chen.tan90_store.pojo.goods.Item;
import csu.chen.tan90_store.pojo.goods.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    public List<Item> getAll(){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return itemDAO.findAll(sort);
    }
    public List<Item> getByCategory(int cid){
        List<Product> products = productService.listByCategory(cid);
        List<Item> items = new ArrayList<>();
        for(Product product:products){
            items.addAll(itemDAO.getAllByProduct(product));
        }
        return items;
    }
    public List<Item> getByProduct(int pid){
        Product product=productService.getById(pid);
        return itemDAO.getAllByProduct(product);
    }
}
