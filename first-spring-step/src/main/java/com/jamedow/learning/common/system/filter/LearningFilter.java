package com.jamedow.learning.common.system.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description
 * <p>
 * Created by 365 on 2016/12/21 0021.
 */
public class LearningFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //将控制器传向下一个filter
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
