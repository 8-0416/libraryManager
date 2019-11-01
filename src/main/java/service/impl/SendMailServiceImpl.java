package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import service.SendMailService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

/**
 * @author FHJ
 * @date 2019/10/31 12:18
 */
@Service("sendMailServiceImpl")
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public String sendMail(String mailAddress) throws MessagingException {
        // 创建邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        // 自定义发件人昵称
        String nick = "";
        try {
            nick = MimeUtility.encodeText("图书管理平台");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 设置发件人邮箱
        mimeMessageHelper.setFrom(new InternetAddress(nick + "<1553162002@qq.com>"));
        // 设置收件人邮箱
        mimeMessageHelper.setTo(mailAddress);
        // 设置邮件的主题
        mimeMessageHelper.setSubject("图书管理平台邮箱验证");
        // 邮件内容

        double code = Math.random();
        if (((int) (code * 10)) < 1) {
            code = code + 0.1;
        }
        int verificationCode = (int) (code * 1000000);

        mimeMessageHelper.setText("亲爱的用户：您好！\n您的验证码为：" + verificationCode + "。请在验证码输入框中输入，以完成操作！2分钟内有效！");
        // 发送邮件
        javaMailSender.send(mimeMessage);
        return String.valueOf(verificationCode);
    }
}
