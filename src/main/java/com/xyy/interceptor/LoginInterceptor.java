package com.xyy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author WhiteRunner
 * @create 2021-11-12 13:53
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Override
    //public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //    System.out.println("经过拦截器");
    //    boolean isLogin=StpUtil.isLogin();
    //    if (isLogin) {
    //        return true;
    //    }else {
    //        //如果未登录,传递给前端未登录的json信息,返回false以拦截到controller的请求
    //        ResponseResult<Object> error = ResponseResult.error("未登录");
    //        String s = JSONObject.toJSONString(error);
    //        returnJson(response,s);
    //        return false;
    //    }
    //}

    //返回json字符串到前端
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        //System.out.println(json);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error", e);
        }
    }

}
