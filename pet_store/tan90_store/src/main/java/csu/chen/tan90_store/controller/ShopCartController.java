package csu.chen.tan90_store.controller;

import csu.chen.tan90_store.pojo.ShopCart;
import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.pojo.user.SimAccount;
import csu.chen.tan90_store.service.ShopCartService;
import csu.chen.tan90_store.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ShopCartController {
    @Autowired
    ShopCartService shopCartService;
    @Autowired
    AccountService accountService;
    @PostMapping(value = "api/shopcart/update")
    public void update(@RequestBody ShopCart shopCarts){
        shopCartService.update(shopCarts);
    }

    @GetMapping(value = "api/shopcart/getList")
    public List<ShopCart> getList(@RequestBody String username){
        return shopCartService.getAllByUsername(username);
    }
}
