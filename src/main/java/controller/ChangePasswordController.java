package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.User;
import service.ChangePasswordService;

/**
 * @author FHJ
 * @date 2019/11/1 16:22
 */
@CrossOrigin
@Controller
public class ChangePasswordController {

    @Autowired
    ChangePasswordService changePasswordService;

    @RequestMapping("/changePassword.do")
    @ResponseBody
    public Message changePassword(String phone, String password, String confirmPassword) {
        Message message = new Message();

        if (phone == null || phone.equals("") || password == null || password.equals("") || confirmPassword == null || confirmPassword.equals("")) {
            return message.setCodeAndPrompt("-3", "必要数据为空！");
        }

        // 校验密码一不一样
        if (!password.equals(confirmPassword)) {
            return message.setCodeAndPrompt("-2", "两次密码不一致！");
        }

        // 根据邮箱查看是否有此用户存在
        User user = changePasswordService.findUserByPhone(phone);

        if (user == null) {
            return message.setCodeAndPrompt("0", "用户不存在！");
        }

        // 根据邮箱修改密码
        Integer flag = changePasswordService.updatePasswordByPhone(phone, password);

        if (flag == 1) {
            return message.setCodeAndPrompt("1", "修改密码成功！");
        }

        return message.setCodeAndPrompt("-1", "修改密码失败！");
    }
}
