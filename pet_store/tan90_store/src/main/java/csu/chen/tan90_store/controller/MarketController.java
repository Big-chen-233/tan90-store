package csu.chen.tan90_store.controller;

import csu.chen.tan90_store.pojo.goods.Item;
import csu.chen.tan90_store.pojo.goods.Product;
import csu.chen.tan90_store.service.goods.CategoryService;
import csu.chen.tan90_store.service.goods.ItemService;
import csu.chen.tan90_store.service.goods.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MarketController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ItemService itemService;
    @Autowired
    ProductService productService;

    @GetMapping("/api/categories/{cid}/products")
    public List<Product> listByCategory(@PathVariable("cid") int cid)throws Exception{
        if(0!=cid){
            return productService.listByCategory(cid);
        }else{
            return productService.list();
        }
    }
    @GetMapping(value = "/api/categories/{cid}/products/{pid}/items")
    public List<Item> listByProducts(@PathVariable("cid") int cid,@PathVariable("pid") int pid){
        if(0==pid){
            //返回cid对应的items
            if(cid==0){
                List<Item> tmp = itemService.getAll();
                return tmp;
            }else{
                List<Item> tmp =itemService.getByCategory(cid);
                return tmp;
            }
        }else{
            //cid的值无关紧要
            List<Item> tmp =itemService.getByProduct(pid);
            return tmp;
        }
    }
}
