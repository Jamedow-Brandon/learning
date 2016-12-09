package com.jamedow.learning.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {

    @RequestMapping("hello")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1、收集参数、验证参数
        //2、绑定参数到命令对象
        //3、将命令对象传入业务对象进行业务处理
        //4、选择下一个页面
        ModelAndView view = new ModelAndView();
        //添加模型数据 可以是任意的POJO对象
        view.addObject("name", "Jameodw!");
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        view.setViewName("hello");
        return view;
    }
}
