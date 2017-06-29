package com.jamedow.laodoufang.common.system.filter;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Description
 * <p>
 * Created by 365 on 2017/3/21 0021.
 */
//@EnableWebMvc
//@Configuration
public class WeChatConfigurer extends WebMvcConfigurerAdapter {

//    @Autowired
//    private HgsProp hgsProp;


    /**
     * 配置拦截器
     *
     * @param registry
     * @author lance
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExCludeInterceptor())
                .excludePathPatterns("/**");
//        registry.addInterceptor(new WeChatInterceptor(hgsProp))
//                .addPathPatterns("/project/**", "/customer/**");

    }
}
