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
        return shopCartDAO.getAllByUsername(username);
    }
    public void update(ShopCart shopCart){

            shopCartDAO.save(shopCart);

    }
}
