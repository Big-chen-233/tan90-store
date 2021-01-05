package csu.chen.tan90_store.service.user;

import csu.chen.tan90_store.DAO.user.SimAccountDAO;
import csu.chen.tan90_store.pojo.user.SimAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimAccountService {
    @Autowired
    SimAccountDAO simAccountDAO;

    public boolean isExist(String username){
        return null!=simAccountDAO.getByUsername(username);
    }
    public void add(SimAccount simAccount){
        simAccountDAO.save(simAccount);
    }
    public SimAccount getByUsername(String username){
        return simAccountDAO.getByUsername(username);
    }
}
