package service;

import po.User;

/**
 * @author FHJ
 * @date 2019/11/1 16:16
 */
public interface ChangePasswordService {

    // 根据邮箱修改密码
    Integer updatePasswordByPhone(String phone, String password);

    // 根据邮箱查询用户
    User findUserByPhone(String phone);
}
