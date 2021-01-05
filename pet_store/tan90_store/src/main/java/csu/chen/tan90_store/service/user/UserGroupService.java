package csu.chen.tan90_store.service.user;

import csu.chen.tan90_store.DAO.user.UserGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService {
    @Autowired
    UserGroupDAO userGroupDAO;
}
