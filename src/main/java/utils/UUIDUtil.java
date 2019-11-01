package utils;

import java.util.UUID;

/**
 * @author 0416
 * @date 2019/11/1
 **/
public class UUIDUtil {

    /**
     * 获取六位随机验证码
     * @return
     */
    public static int getVerifyCode(){
        int temp = (int)(Math.random()*(999999-100000+1)+100000);
        System.out.println(temp);
        return temp;
    }
}