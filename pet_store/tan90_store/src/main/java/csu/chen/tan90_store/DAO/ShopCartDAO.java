package csu.chen.tan90_store.DAO;

import csu.chen.tan90_store.pojo.ShopCart;
import csu.chen.tan90_store.pojo.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopCartDAO extends JpaRepository<ShopCart ,Integer> {
    List<ShopCart> getAllByUsernameAndIsPay(String username,Integer isPay);
}
