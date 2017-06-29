package com.jamedow.laodoufang.common.system.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description 微信token拦截器
 * <p>
 * Created by 365 on 2017/3/21 0021.
 */
public class WeChatInterceptor implements HandlerInterceptor {

//    private final HgsProp hgsProp;
    //初始化微信所有菜单下的链接，供权限判断使用

    //注入hgsProp配置文件Bean
//    public WeChatInterceptor(HgsProp hgsProp) {
//        this.hgsProp = hgsProp;
//    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {

        System.out.println(">>>WeChatInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        boolean flag = false;
        if (flag) {
            response.sendRedirect("http://www.baidu.com");
            return false;//重定向之后，需要return false;
        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView
    ) throws Exception {
        System.out.println(">>>WeChatInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    ) throws Exception {
        System.out.println(">>>WeChatInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
