package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;

import java.util.HashMap;
import java.util.Map;

import static utils.SendMessageUtil.execute;


/**
 * @author 0416
 * @date 2019/11/1
 **/
@CrossOrigin
@Controller
public class SendMessageController {

    @RequestMapping("/sendMessage.do")
    @ResponseBody
    public Message sendMessage(String phone){
        Message message = new Message();

        if(phone == null || phone.equals("")){
            return message.setCodeAndPrompt("0", "手机号码为空！");
        }

        try{
            Integer verifyCode = execute(phone);
            if(verifyCode != null){
                Map<String, Object> map = new HashMap<>(2);
                map.put("verifyCode", verifyCode);
                Long outTime = System.currentTimeMillis() + 2 * 60 * 1000;
                map.put("outTime", outTime);
                message.setReturnData(map);
                message.setCodeAndPrompt("1", "获取验证码成功！");
                return message;
            }
            return message.setCodeAndPrompt("-1", "获取验证码失败！");
        }catch (Exception e){
            e.printStackTrace();
            return message.setCodeAndPrompt("-1", "获取验证码失败！");
        }
    }
}
