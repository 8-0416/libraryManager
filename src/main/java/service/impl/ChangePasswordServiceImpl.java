package service.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import service.ChangePasswordService;

/**
 * @author FHJ
 * @date 2019/11/1 16:18
 */
@Service("changePasswordService")
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    UserDao userDao;

    @Override
    public Integer updatePasswordByEmail(String email, String password) {
        return userDao.updatePasswordByEmail(email, password);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
