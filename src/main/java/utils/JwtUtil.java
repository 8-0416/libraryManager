package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithmSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FHJ
 * @date 2019/10/27 09:12
 * <p>
 * JWT工具类
 */
public class JwtUtil {
    public static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // 设置过期时间为30分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    // JWT加密密钥
    private static final String TOKEN_SECRET = "JKV1QiLCJhbGciOiJIUzI1NiJ9";

    /**
     * 生成JWT字符串
     *
     * @param userId：学号或者工号
     * @return
     */
    public static String generateJWT(String userId) {

        // 过期时间
        Date expirationTime = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        try {
            // 加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> headerMap = new HashMap<>(2);
            headerMap.put("type", "JWT");
            headerMap.put("alg", "HS256");
            String jwt = JWT.create().withHeader(headerMap)
                    .withClaim("userId", userId)
                    .withExpiresAt(expirationTime)
                    .sign(algorithm);
            return jwt;
        } catch (UnsupportedEncodingException e) {
            log.error("jwt生成失败！\n" + e.getMessage());
            return null;
        }
    }


    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verifyToken(String token) {
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(token);
            log.info("超时时间：" + decodedJWT.getExpiresAt());
            log.info("载体信息：" + decodedJWT.getClaim("userId").asString());
            log.info("算法：" + decodedJWT.getAlgorithm());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 获取登陆用户ID
     */
    public static int getUid(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return 0;
        }
    }

}
