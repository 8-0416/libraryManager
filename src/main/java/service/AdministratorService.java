package service;

import org.springframework.stereotype.Service;
import po.User;

/**
 * @author 0416
 * @date 2019/10/31
 **/
public interface AdministratorService {
    void addAdministrator(User user);
}
