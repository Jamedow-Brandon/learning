package com.jamedow.learning.web;

import com.jamedow.learning.entity.UserEntity;
import com.jamedow.learning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }


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
    public String addUser(UserEntity userEntity, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        logger.info("add user {} start", userEntity.getUsername());
        try {
            userService.insertUser(userEntity);
        } catch (Exception e) {
            logger.error("add user {} error", userEntity.getUsername());
            redirectAttributes.addFlashAttribute("message", "error");
        }
        redirectAttributes.addFlashAttribute("message", "ok");
        return "redirect:hello";
    }
}
