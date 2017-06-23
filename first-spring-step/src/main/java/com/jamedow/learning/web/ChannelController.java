package com.jamedow.learning.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/6/23.
 */
@Controller
@RequestMapping("/")
public class ChannelController {
    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

    @RequestMapping(value = "channel")
    public ModelAndView channel(String channelId) {
        ModelAndView view = new ModelAndView();
        view.addObject("channelId", "channelId");
        view.setViewName("channel");
        return view;
    }
}
