package com.xyy.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.xyy.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
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
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("经过拦截器");
        boolean isLogin= StpUtil.isLogin();
        if (isLogin) {
            return true;
        }else {
            //如果未登录,抛出异常统一处理
            throw new RuntimeException("未登陆");
        }
    }

    //返回json字符串到前端
    //private void returnJson(HttpServletResponse response, String json) throws Exception{
    //    response.setCharacterEncoding("UTF-8");
    //    response.setContentType("text/html; charset=utf-8");
    //    //System.out.println(json);
    //    try (PrintWriter writer = response.getWriter()) {
    //        writer.print(json);
    //    } catch (IOException e) {
    //        logger.error("response error", e);
    //    }
    //}

}
