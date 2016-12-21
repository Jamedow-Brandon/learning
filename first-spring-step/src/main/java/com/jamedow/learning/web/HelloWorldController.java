package com.jamedow.learning.web;

import com.jamedow.learning.entity.Users;
import com.jamedow.learning.service.UsersService;
import com.jamedow.learning.wechat.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

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
            usersService.insertUser(users);
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

    @RequestMapping("checkwechatcallback")
    public String checkWeChatCallback(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "msg_signature") String sVerifyMsgSig,
                                      @RequestParam(value = "timestamp") String sVerifyTimeStamp,
                                      @RequestParam(value = "nonce") String sVerifyNonce,
                                      @RequestParam(value = "echostr") String sVerifyEchoStr) throws Exception {


        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

        String sEchoStr = null; //需要返回的明文
        try {
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
                    sVerifyNonce, sVerifyEchoStr);
            System.out.println("verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            // HttpUtils.SetResponse(sEchoStr);
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
        }
        return sEchoStr;
    }
}
