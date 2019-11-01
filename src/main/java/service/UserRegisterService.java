package service;

import dto.RegisterDto;
import po.User;

/**
 * @author FHJ
 * @date 2019/10/31 23:11
 */
public interface UserRegisterService {
    // 添加用户
    Integer addUser(RegisterDto registerDto);

    // 根据userId查询用户
    User findUserByUserId(String userId);

    // 根据邮箱查询用户
    User findUserByEmail(String email);
}
