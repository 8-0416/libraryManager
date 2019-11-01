package utils;

import java.net.URLEncoder;

/**
 * @author 0416
 * @date 2019/11/1
 **/
public class SendMessageUtil {

/*    private static String accountSid = Config.ACCOUNT_SID;
    private static String smsContent = "【广东金融学院图书馆】尊敬的用户，您的短信验证码为"+
            UUIDUtil.getVerifyCode()+",2分钟内有效，若非本人操作请忽略。";*/

    /**
     * 验证码通知短信
     */
/*    public static String execute(String mobile)
    {
        String tmpSmsContent = null;
        try{
            tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
        }catch(Exception e){

        }
        String url = Config.BASE_URL;
        String body = "accountSid=" + accountSid + "&to=" + mobile + "&smsContent=" + tmpSmsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);
        return result;
    }*/

    /**
     * 短信发送(验证码通知)
     */
    public static Integer execute(String phone) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
        sb.append("&to").append("=").append(phone);
        sb.append("&param").append("=").append(URLEncoder.encode("", "UTF-8"));
        /*sb.append("&templateid").append("=").append("238757");*/
        // 验证码
        Integer verifyCode = UUIDUtil.getVerifyCode();

        sb.append("&smsContent").append("=").append(URLEncoder.encode(
                "【广东金融学院图书馆】尊敬的用户，您的短信验证码为" + verifyCode +
                        "，2分钟内有效，若非本人操作请忽略。", "UTF-8"));
        String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        String result = HttpUtil.post(Config.BASE_URL, body);
        System.out.println(result);

        return verifyCode;
    }

}
