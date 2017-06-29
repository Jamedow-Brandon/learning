package com.jamedow.laodoufang.web;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import com.alibaba.fastjson.JSONObject;
import com.jamedow.laodoufang.entity.Category;
import com.jamedow.laodoufang.entity.Users;
import com.jamedow.laodoufang.service.CategoryService;
import com.jamedow.laodoufang.service.UsersService;
import com.jamedow.laodoufang.utils.rabbitmq.RabbitMQUtils;
import com.jamedow.laodoufang.utils.redis.RedisPoolManager;
import com.jamedow.laodoufang.utils.webcollector.BingCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private final String VIRTUAL_HOST = "webcollector";
    private final String QUEUE = "queue";
    @Autowired
    private UsersService usersService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisPoolManager redis;

    @RequestMapping(value = "")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");

        List<Category> categories = categoryService.getAllCategories();
        view.addObject("categories", categories);

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

        try {
            String keyword = "公司";
            int maxPageNum = 3;
            BingCrawler crawler = new BingCrawler("depth_crawler", false);
            for (int pageNum = 1; pageNum <= maxPageNum; pageNum++) {
                String url = BingCrawler.createBingUrl(keyword, pageNum);
                crawler.addSeed(new CrawlDatum(url)
                        .putMetaData("keyword", keyword)
                        .putMetaData("pageNum", pageNum + "")
                        .putMetaData("pageType", "searchEngine")
                        .putMetaData("depth", "1"));
            }

            crawler.start(maxPageNum);

            RabbitMQUtils.consumerMessage(VIRTUAL_HOST, QUEUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return view;
    }

    @RequestMapping("/toHomePage")
    public ModelAndView toHomePage() {

        ModelAndView view = new ModelAndView();
        view.setViewName("homePage");
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
        redis.setCacheWithSec("name", "aa", 1000);
        String name = redis.getCache("name");
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