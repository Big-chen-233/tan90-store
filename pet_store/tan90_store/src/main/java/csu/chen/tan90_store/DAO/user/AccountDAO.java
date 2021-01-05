package csu.chen.tan90_store.DAO.user;

import csu.chen.tan90_store.pojo.user.Account;
import csu.chen.tan90_store.pojo.user.SimAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account ,Integer> {
    Account getBySimAccount(SimAccount simAccount);
}
