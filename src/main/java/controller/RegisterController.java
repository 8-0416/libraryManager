package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import utils.SendMessageUtil;

/**
 * @author 0416
 * @date 2019/11/1
 **/
@CrossOrigin
@Controller
public class RegisterController {

    @RequestMapping("/sendMessage.do")
    @ResponseBody
    public Message sendMessage(String mobile){
        Message message = new Message();
        try{
            new SendMessageUtil().execute(mobile);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }
}
