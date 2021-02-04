package csu.chen.tan90_store.controller;

import csu.chen.tan90_store.pojo.ShopCart;
import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.pojo.user.SimAccount;
import csu.chen.tan90_store.result.Result;
import csu.chen.tan90_store.result.ResultFactory;
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

    @PostMapping(value = "api/shopcart/getList")
    public List<ShopCart> getList(@RequestBody String username){
        username = username.split("=")[0];
        return shopCartService.getAllByUsername(username);
    }
    @PostMapping(value = "api/shopcart/buy")
    public void buyAll(@RequestBody String username){
        username = username.split("=")[0];
        shopCartService.clear(username);
    }
    @PostMapping(value = "api/shopcart/getOrder")
    public List<ShopCart> getOrder(@RequestBody String username){
        username = username.split("=")[0];
        return shopCartService.getAllByUsername(username,1);
    }
    @PostMapping(value = "api/shopcart/valChange")
    public Result valChange(@RequestBody ShopCart shopCart){
        shopCartService.valChange(shopCart);
        return ResultFactory.buildSuccessResult("值已更改");
    }
    @PostMapping(value = "api/shopcart/delete")
    public Result delete(@RequestBody ShopCart shopCart){
        shopCartService.delete(shopCart);
        return ResultFactory.buildSuccessResult("已经删除");
    }
}
