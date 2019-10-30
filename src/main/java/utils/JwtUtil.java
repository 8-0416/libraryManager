package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FHJ
 * @date 2019/10/27 09:12
 * JWT工具类
 */
public class JwtUtil {
    public static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // 超时时间为30分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    // token密钥
    private static final String TOKEN_SECRET = "r2a35g7s96g4j2k7s3a6t7eb14k27gkq";

    /**
     * 生成签名，返回jwt字符串
     *
     * @param userId
     * @return
     */
    public static String sign(String userId) {
        try {
            // 过期时间
            Date expiration_time = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("type", "JWT");
            header.put("alg", "HS256");
            // 附带userId信息，生成签名
            return JWT.create().withHeader(header).withClaim("userId", userId)
                    .withExpiresAt(expiration_time).sign(algorithm);
        } catch (Exception e) {
            log.error("token生成发生错误");
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 校验token是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(token);
            log.info("超时时间：" + decodedJWT.getExpiresAt());
            log.info("载体信息：" + decodedJWT.getClaim("userId").asString());
            log.info("算法：" + decodedJWT.getAlgorithm());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取过期时间
     *
     * @param token
     * @return
     */
    public static Date getExpiresAt(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

    /**
     * 获取token中的userId
     *
     * @param token
     * @return
     */
    public  static String getUserId(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId").asString();
    }
}

