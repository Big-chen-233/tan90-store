package csu.chen.tan90_store.service;

import csu.chen.tan90_store.DAO.ShopCartDAO;
import csu.chen.tan90_store.pojo.ShopCart;
import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCartService {
    @Autowired
    ShopCartDAO shopCartDAO;
    @Autowired
    AccountService accountService;
    public List<ShopCart> getAllByUsername(String username){
        List<ShopCart> shopCarts =  shopCartDAO.getAllByUsernameAndIsPay(username,0);
        return shopCarts;
    }
    public List<ShopCart> getAllByUsername(String username,int isPay){
        List<ShopCart> shopCarts =  shopCartDAO.getAllByUsernameAndIsPay(username,isPay);
        return shopCarts;
    }
    public void update(ShopCart shopCart){
        List<ShopCart> shopCarts = getAllByUsername(shopCart.getUsername());
        for(ShopCart shopCart1:shopCarts){
            if(shopCart1.getItem().getId()==shopCart.getItem().getId()){
                shopCart1.setQuantity(shopCart1.getQuantity()+shopCart.getQuantity());
                shopCartDAO.save(shopCart1);
                return;
            }
        }
        shopCartDAO.save(shopCart);

    }
    public void clear(String username){
        List<ShopCart> shopCarts = getAllByUsername(username);
        for(ShopCart shopCart:shopCarts){
            shopCart.setIsPay(1);
            shopCartDAO.save(shopCart);
        }
    }

    public void valChange(ShopCart shopCart){
        List<ShopCart> shopCarts = getAllByUsername(shopCart.getUsername());
        for(ShopCart shopCart1:shopCarts){
            if(shopCart1.getItem().getId()==shopCart.getItem().getId()){
                shopCart1.setQuantity(shopCart.getQuantity());
                shopCartDAO.save(shopCart1);
                return;
            }
        }
    }

    public void delete(ShopCart shopCart){
        List<ShopCart> shopCarts = getAllByUsername(shopCart.getUsername());
        for(ShopCart shopCart1:shopCarts){
            if(shopCart1.getItem().getId()==shopCart.getItem().getId()){
                shopCart1.setIsPay(500);
                shopCartDAO.save(shopCart1);
                return;
            }
        }
    }
}
