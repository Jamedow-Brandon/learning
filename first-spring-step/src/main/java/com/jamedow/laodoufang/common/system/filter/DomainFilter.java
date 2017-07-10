package com.jamedow.laodoufang.common.system.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description
 * <p>
 * Created by 365 on 2016/12/21 0021.
 */
public class DomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!request.getRequestURL().toString().contains("www.laodou.site") && !request.getRequestURL().toString().contains("localhost")) {
            ((HttpServletResponse) servletResponse).sendError(404, "对不起，您访问的页面不存在");
            return;
        }
        //将控制器传向下一个filter
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
