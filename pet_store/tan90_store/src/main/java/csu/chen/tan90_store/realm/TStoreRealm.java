package csu.chen.tan90_store.realm;

import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.pojo.user.SimAccount;
import csu.chen.tan90_store.service.user.AccountService;
import csu.chen.tan90_store.service.user.SimAccountService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class TStoreRealm extends AuthorizingRealm {
    @Autowired
    private SimAccountService accountService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        SimAccount user = accountService.getByUsername(userName);
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        return new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
    }
}
