package interceptor;

import po.Message;
import utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {
    public static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("access_token");
        Date date = new Date(System.currentTimeMillis());
        //token不存在
        if (null != token) {
            //验证token是否正确
            boolean result = JwtUtil.verify(token);
            if (result) {
                // token过期
                if(new Date().getTime() > JwtUtil.getExpiresAt(token).getTime()){
                    // 过期超过半个月
                    if (new Date().getTime() > (JwtUtil.getExpiresAt(token).getTime() + 15 * 24 * 60 * 60 * 1000)){
                        System.out.println("token过期超过半个月！");
                        Message message = new Message();
                        message.setCodeAndPrompt("-3", "token过期超过半个月，请重新登录！");
                        responseMessage(response, message);
                        return false;
                    }
                    // 过期没超过半个月，生成新的token
                    response.addHeader("access_token", JwtUtil.sign(JwtUtil.getUserId(token)));
                    return true;
                }
                return true;
            } else {
                System.out.println("token认证错误！");
                Message message = new Message();
                message.setCodeAndPrompt("-2", "token认证错误！");
                responseMessage(response, message);
                return false;
            }
        } else {
            Message message = new Message();
            message.setCodeAndPrompt("-1", "token为空，用户未登录！");
            responseMessage(response, message);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("加载完成");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("结束");
    }

    /**
     * 返回信息给客户端
     *
     * @param response
     * @param message
     */
    private void responseMessage(HttpServletResponse response, Message message) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            writer.println(message);
        } catch (IOException e) {
            log.error(message.getPrompt());
            e.printStackTrace();
        } finally {
            if (writer != null){
                writer.flush();
                writer.close();
            }
        }
    }
}
