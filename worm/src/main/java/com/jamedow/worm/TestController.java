package com.jamedow.worm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/10/23.
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("worm")
    public void testWorm(){
        Spider.create(new RepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
