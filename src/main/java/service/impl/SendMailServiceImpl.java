package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import service.SendMailService;

import javax.annotation.Resource;

/**
 * @author FHJ
 * @date 2019/10/31 12:18
 */
@Service("sendMailServiceImpl")
public class SendMailServiceImpl implements SendMailService {
    @Override
    public String sendMail(String qqMail) {

        JavaMailSender javaMailSender;



        return null;
    }
}
