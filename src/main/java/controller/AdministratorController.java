package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.User;
import service.AdministratorService;
import service.UserLoginService;

/**
 * @author 0416
 * @date 2019/10/31
 **/
@CrossOrigin
@Controller
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;
    @Autowired
    UserLoginService userLoginService;

    @RequestMapping("/addAdministrator.do")
    @ResponseBody
    public Message addAdministrator(@RequestBody User user){
        Message message = new Message();
        String userId = user.getUserId();
        String password = user.getPassword();
        String userName = user.getName();

        if(userId == null || "".equals(userId)){
            return message.setCodeAndPrompt("-1", "账号不能为空");
        }

        if(password == null || "".equals(password)){
            return message.setCodeAndPrompt("-1", "密码不能为空");
        }

        if(userName == null || "".equals(userName)){
            return message.setCodeAndPrompt("-1", "用户名不能为空");
        }

        User temp = userLoginService.findUserByUserId(userId);
        if(temp != null){
            return message.setCodeAndPrompt("-1", "该账号已存在");
        }

        user.setPermissions(1);
        try{
            administratorService.addAdministrator(user);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success("添加成功");
    }
}
