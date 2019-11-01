package controller;

import dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.User;
import service.UserRegisterService;

/**
 * @author FHJ
 * @date 2019/10/31 23:15
 */
@CrossOrigin
@Controller
public class UserRegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @RequestMapping("/userRegister.do")
    @ResponseBody
    public Message userRegister(RegisterDto registerDto){
        Message message = new Message();

        String userId = registerDto.getUserId();
        String name = registerDto.getName();
        String phone = registerDto.getPhone();
        String password = registerDto.getPassword();
        String confirmPassword = registerDto.getConfirmPassword();
        Integer permissions = registerDto.getPermissions();

        if(userId == null || userId.equals("") || name == null || name.equals("") || password == null || password.equals("") || confirmPassword == null || confirmPassword.equals("") || permissions == null || !(permissions == 0 || permissions == 1)){
            message.setCodeAndPrompt("-2", "用户信息不全!");
            return message;
        }

        // 根据userId查询用户是否已经存在
        User user = userRegisterService.findUserByUserId(userId);
        if (user != null){
            message.setCodeAndPrompt("-3", "学号(工号)已被注册!");
            return message;
        }

        // permissions==0,说明是学生,则需要手机
        if(permissions == 0 && (phone == null || phone.equals(""))){
            message.setCodeAndPrompt("-2", "用户信息不全!");
            return message;
        }

        if(permissions == 0){
            User user1 = userRegisterService.findUserByPhone(phone);
            if (user1 != null){
                message.setCodeAndPrompt("-5", "手机号已被注册!");
                return message;
            }
        }

        if(!password.equals(confirmPassword)){
            message.setCodeAndPrompt("-4", "密码不一致!");
            return message;
        }

        Integer flag = userRegisterService.addUser(registerDto);

        if (flag == 1){
            message.setCodeAndPrompt("1", "注册成功!");
            return message;
        }

        message.setCodeAndPrompt("-1", "注册失败!");
        return message;
    }
}
