package dao;

import dto.RegisterDto;
import org.apache.ibatis.annotations.Param;
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

    // 根据邮箱查询用户
    User findUserByEmail(String email);

    // 根据邮箱修改密码
    Integer updatePasswordByEmail(@Param("email") String email, @Param("password") String password);
}
