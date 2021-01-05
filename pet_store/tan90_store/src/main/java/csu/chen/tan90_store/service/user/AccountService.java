package csu.chen.tan90_store.service.user;

import csu.chen.tan90_store.DAO.user.AccountDAO;
import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.pojo.user.SimAccount;
import csu.chen.tan90_store.pojo.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    SimAccountService simAccountService;
    @Autowired
    UserGroupService userGroupService;

    public void addAccount(Account account){
        accountDAO.save(account);
    }
    public Account getByUsername(String username){
        SimAccount simAccount = simAccountService.getByUsername(username);
        return accountDAO.getBySimAccount(simAccount);
    }
}
