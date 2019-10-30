package utils;

import org.springframework.util.DigestUtils;

/**
 * @author FHJ
 * @date 2019/10/30 12:35
 */
public class MD5Util {
    public static final String KEY_MD5 = "t1f3sht65b1fd";

    /**
     * 生成md5
     * @param pass
     * @return
     */
    public static String encryption(String pass) {
        String base = pass +"/"+KEY_MD5;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
