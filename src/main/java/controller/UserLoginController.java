package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.User;
import service.UserLoginService;
import utils.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FHJ
 * @date 2019/10/30 16:58
 */
@CrossOrigin
@Controller
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    @RequestMapping("/userLogin.do")
    @ResponseBody
    public Message userLogin(String userId, String password, Integer permissions) {
        Message message = new Message();
        if (userId == null || userId.equals("") || password == null || password.equals("") || permissions == null || !(permissions == 0 || permissions == 1)) {
            return message.setCodeAndPrompt("-1", "必要数据为空或者数值不符合要求！");
        }

        // 根据userId查询用户
        User user = userLoginService.findUserByUserId(userId);

        if (user == null) {
            return message.setCodeAndPrompt("0", "没有此用户！");
        }

        if (!user.getPassword().equals(password)) {
            return message.setCodeAndPrompt("2", "用户名或密码错误！");
        }

        if (!user.getPermissions().equals(permissions)) {
            return message.setCodeAndPrompt("3", "用户权限错误");
        }

        // 登录信息校验正确
        String token = JwtUtil.sign(userId);
        message.setCodeAndPrompt("1", "用户登录成功！");
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("user", user);
        message.setReturnData(map);
        return message;
    }
}
