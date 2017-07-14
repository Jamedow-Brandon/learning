package com.jamedow.laodoufang.web.manage;

import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.service.TagsRelService;
import com.jamedow.laodoufang.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyo on 2017/7/12.
 */
@Controller
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private TagsRelService tagsRelService;

    @RequestMapping("/tagsManage")
    public ModelAndView tagsManage(){

        List<Tags> classifyList = new ArrayList<Tags>();
        classifyList = tagsService.queryClassify();
        ModelAndView view = new ModelAndView();
        view.addObject("classifyList",classifyList);
        view.setViewName("manage/tagsManage");
        return view;
    }

    @RequestMapping("/deleteClassify")
    @ResponseBody
    public String deleteClassify(int classifyId){

        String result = tagsService.deleteTags(classifyId);
        return result;
    }

    @RequestMapping("/toAddClassify")
    public ModelAndView toAddClassify(){

        ModelAndView view = new ModelAndView();
        view.setViewName("manage/add_classify");
        return view;
    }

    @RequestMapping("/addClassify")
    @ResponseBody
    public Tags addClassify(String name){


        Tags tags = new Tags();
        tags.setName(name);

        tags = tagsService.saveClassify(tags);
        return tags;
    }

    @RequestMapping("/editorClassify")
    @ResponseBody
    public Tags editorClassify(int id,String name){


        Tags tags = new Tags();
        tags.setId(id);
        tags.setName(name);

        tags = tagsService.saveClassify(tags);
        return tags;
    }


}
