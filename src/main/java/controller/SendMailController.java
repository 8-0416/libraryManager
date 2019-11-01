package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import service.SendMailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FHJ
 * @date 2019/10/31 16:13
 */
@CrossOrigin
@Controller
public class SendMailController {

    @Autowired
    SendMailService sendMailService;

    @RequestMapping("/sendMail.do")
    @ResponseBody
    public Message sendMail(String mailAddress){
        Message message = new Message();

        if(mailAddress == null || mailAddress.equals("")){
            message.setCodeAndPrompt("0", "邮箱地址为空!");
            return message;
        }

        try {
            // 发送邮件
            String verificationCode = sendMailService.sendMail(mailAddress);
            message.setCodeAndPrompt("1", "验证码发送成功!");
            // 设置验证码过期时间
            Long outTime = System.currentTimeMillis() + 2 * 60 + 1000;
            Map<String, Object> map = new HashMap<>();
            map.put("verificationCode", verificationCode);
            map.put("outTime", outTime);
            message.setReturnData(map);
            return message;
        } catch (IOException e) {
            message.setCodeAndPrompt("-1", "验证码发送失败!");
            // e.printStackTrace();
            return message;
        } catch (MessagingException e) {
            message.setCodeAndPrompt("-1", "验证码发送失败!");
            // e.printStackTrace();
            return message;
        }
    }
}
