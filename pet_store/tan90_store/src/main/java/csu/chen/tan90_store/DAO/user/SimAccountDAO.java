package csu.chen.tan90_store.DAO.user;

import csu.chen.tan90_store.pojo.user.SimAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimAccountDAO extends JpaRepository<SimAccount ,Integer> {
    SimAccount getByUsername(String username);
}
