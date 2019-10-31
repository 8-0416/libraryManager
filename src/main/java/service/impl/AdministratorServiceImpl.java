package service.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.User;
import service.AdministratorService;

/**
 * @author 0416
 * @date 2019/10/31
 **/
@Service("administratorService")
@Transactional(rollbackFor = Exception.class)
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    UserDao userDao;

    @Override
    public void addAdministrator(User user) {
        userDao.addAdministrator(user);
    }
}
