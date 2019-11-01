package utils;

import java.net.URLEncoder;

/**
 * @author 0416
 * @date 2019/11/1
 **/
public class SendMessageUtil {

    /**
     * 短信发送(验证码通知)
     */
    public void execute(String mobile) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
        sb.append("&to").append("=").append(mobile);
        sb.append("&param").append("=").append(URLEncoder.encode("","UTF-8"));
		sb.append("&smsContent").append("=").append( URLEncoder.encode(
		        "【广东金融学院图书馆】尊敬的用户，您的短信验证码为" + UUIDUtil.getUUID() +
                ",2分钟内有效，若非本人操作请忽略。","UTF-8"));
        String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        String result = HttpUtil.post(Config.BASE_URL, body);
        System.out.println(result);
    }

}
