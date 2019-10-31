package dao;

import dto.RegisterDto;
import po.User;

/**
 * @author FHJ
 * @date 2019/10/30 16:32
 */
public interface UserDao {
    // 根据userId查询用户
    User findUserByUserId(String userId);

    // 添加用户
    Integer addUser(RegisterDto registerDto);
}
