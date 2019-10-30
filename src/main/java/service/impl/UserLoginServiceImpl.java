package service.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import service.UserLoginService;

/**
 * @author FHJ
 * @date 2019/10/30 17:02
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserDao userDao;

    @Override
    public User findUserByUserId(String userId) {
        return userDao.findUserByUserId(userId);
    }
}
