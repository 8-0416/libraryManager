package service.impl;

import dao.UserDao;
import dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import service.UserRegisterService;

/**
 * @author FHJ
 * @date 2019/10/31 23:13
 */
@Service("userRegisterService")
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    UserDao userDao;

    @Override
    public Integer addUser(RegisterDto registerDto) {
        return userDao.addUser(registerDto);
    }

    @Override
    public User findUserByUserId(String userId) {
        return userDao.findUserByUserId(userId);
    }
}
