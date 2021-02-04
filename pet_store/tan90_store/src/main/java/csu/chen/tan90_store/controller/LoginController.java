package csu.chen.tan90_store.controller;

import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.pojo.user.SimAccount;
import csu.chen.tan90_store.result.Result;
import csu.chen.tan90_store.result.ResultFactory;
import csu.chen.tan90_store.service.user.AccountService;
import csu.chen.tan90_store.service.user.SimAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
@CrossOrigin
public class LoginController {
    @Autowired
    SimAccountService simAccountService;
    @Autowired
    AccountService accountService;

    @PostMapping(value = "/api/login/login")
    @ResponseBody
    public Result login(@RequestBody SimAccount simAccount){
        String username = simAccount.getUsername();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,simAccount.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(username);
        }catch (AuthenticationException e){
            String message = "账号密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }

    @PostMapping(value = "/api/login/register")
    @ResponseBody
    public Result register(@RequestBody SimAccount user){
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist =simAccountService.isExist(username);
        if(exist){
            String msg = "用户名已被使用";
            return ResultFactory.buildFailResult(msg);
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodePassword = new SimpleHash("md5",password,salt,times).toString();
        user.setSalt(salt);
        user.setPassword(encodePassword);
        simAccountService.add(user);
        return ResultFactory.buildSuccessResult(user);
    }

    @PostMapping(value = "/api/login/isExist")
    @ResponseBody
    public Result isExist(@RequestBody String username){
        username = username.split("=")[0];
        boolean exist = simAccountService.isExist(username);
        if(exist) return ResultFactory.buildFailResult("用户名已被使用");
        else return ResultFactory.buildSuccessResult("可以注册");
    }

    @PostMapping(value = "/api/login/finish")
    @ResponseBody
    public Result finish(@RequestBody Account account){
        SimAccount simAccount =account.getSimAccount();
        if(!simAccountService.isExist(simAccount.getUsername())){
            return ResultFactory.buildFailResult("不存在的用户名");
        }
        register(simAccount);
        String name = account.getName();
        String telephone = account.getTelephone();
        String country = account.getCountry();
        String state = account.getState();
        String city = account.getCity();
        String detailLocate = account.getDetailLocate();
        name = HtmlUtils.htmlEscape(name);
        telephone = HtmlUtils.htmlEscape(telephone);
        country = HtmlUtils.htmlEscape(country);
        state = HtmlUtils.htmlEscape(state);
        city = HtmlUtils.htmlEscape(city);
        detailLocate = HtmlUtils.htmlEscape(detailLocate);
        account.setCity(city);
        account.setName(name);
        account.setState(state);
        account.setTelephone(telephone);
        account.setCountry(country);
        account.setDetailLocate(detailLocate);
        accountService.addAccount(account);
        return ResultFactory.buildSuccessResult(account);
    }
    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String msg ="成功登出";
        return ResultFactory.buildSuccessResult(msg);
    }

    @ResponseBody
    @GetMapping(value = "api/login/authentication")
    public String authentication(){
        return "成功";
    }

}
