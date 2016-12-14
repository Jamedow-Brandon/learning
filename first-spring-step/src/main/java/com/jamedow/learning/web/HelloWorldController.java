package com.jamedow.learning.web;

import com.jamedow.learning.entity.UserEntity;
import com.jamedow.learning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("hello")
    public ModelAndView hello() {
        //1、收集参数、验证参数
        //2、绑定参数到命令对象
        //3、将命令对象传入业务对象进行业务处理
        //4、选择下一个页面
        ModelAndView view = new ModelAndView();
        //添加模型数据 可以是任意的POJO对象
        view.addObject("user", new UserEntity());
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        view.setViewName("hello");
        return view;
    }

    @RequestMapping("adduser")
    public ModelAndView addUser(UserEntity userEntity) throws Exception {
        logger.info("add user {} start", userEntity.getUsername());
        ModelAndView view = new ModelAndView();
        try {
            userService.insertUser(userEntity);
        } catch (Exception e) {
            logger.error("add user {} error", userEntity.getUsername());
        }
        //添加模型数据 可以是任意的POJO对象
        view.addObject("user", userEntity);
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        view.setViewName("hello");
        return view;
    }
}
