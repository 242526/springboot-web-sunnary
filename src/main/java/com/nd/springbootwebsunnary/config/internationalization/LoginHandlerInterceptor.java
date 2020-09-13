package com.nd.springbootwebsunnary.config.internationalization;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 拦截器--- 登入检测
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session域中的对象
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            //没有登入， 再去首页 getRequestDispatcher: 转发器
            request.setAttribute("msg","没有权限");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else{
            //登入的， 直接放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
