package com.jamedow.laodoufang.common.system.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(DomainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.debug("request url :[{}]", request.getRequestURL());
        if (!request.getRequestURL().toString().contains("www.laodou.site") && !request.getRequestURL().toString().contains("127.0.0.1")) {
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
