package service;

import po.User;

/**
 * @author FHJ
 * @date 2019/10/30 17:02
 */
public interface UserLoginService {
    // 根据userId查询用户
    User findUserByUserId(String userId);
}
