package com.jamedow.learning.web;

import com.alibaba.fastjson.JSONObject;
import com.jamedow.learning.entity.Users;
import com.jamedow.learning.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private UsersService usersService;
    @Value("${wechat.token}")
    private String sToken;
    @Value("${wechat.encodingaeskey}")
    private String sEncodingAESKey;
    @Value("${wechat.cropid}")
    private String sCorpID;

    @RequestMapping(value = "")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.addObject("name", "jamedow");
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
        view.addObject("user", new Users());
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        view.setViewName("hello");
        return view;
    }

    @RequestMapping("adduser")
    public String addUser(Users users, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        logger.info("add user {} start", users.getUsername());
        try {
            usersService.saveUser(users);
        } catch (Exception e) {
            logger.error("add user {} error", users.getUsername());
            redirectAttributes.addFlashAttribute("message", "error");
        }
        redirectAttributes.addFlashAttribute("message", "ok");
        return "redirect:hello";
    }

    @RequestMapping("waterfall")
    public ModelAndView waterfall() {
        ModelAndView view = new ModelAndView();
        view.setViewName("waterfall-layout");
        return view;
    }

    /**
     * 测试方法
     *
     * @return
     */
    @RequestMapping("testO")
    @ResponseBody
    public String testO() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "aa");
        logger.info("==========test1===========");
        return jsonObject.toJSONString();
    }

    /**
     * 测试联动
     *
     * @param key
     * @return
     */
    @RequestMapping("testT")
    @ResponseBody
    public String testT(String key) {
        logger.info("==========test2:key:{}===========", key);
        if (key.equals("aa")) {
            return "yes";
        } else {
            return "no";
        }
    }
}
