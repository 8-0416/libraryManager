package service;

/**
 * @author FHJ
 * @date 2019/10/31 12:15
 */
public interface SendMailService {
    // 发送验证码
    String sendMail(String qqMail);
}
