package com.jamedow.laodoufang.web.manage;

import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.entity.TagsRel;
import com.jamedow.laodoufang.entity.ex.TagsExt;
import com.jamedow.laodoufang.entity.ex.TagsRelExt;
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

    @RequestMapping(value = "/deleteClassify", produces = {"application/text;charset=UTF-8"})
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

    @RequestMapping("/toEditorClassify")
    public ModelAndView toEditorClassify(int classifyId){

        ModelAndView view = new ModelAndView();
        view.addObject("classifyId",classifyId);
        view.setViewName("manage/editor_classify");
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

    @RequestMapping("/searchTagsOneByClassify")
    @ResponseBody
    public List<Tags> searchTagsOneByClassify(int classifyId){

        List<Tags> tagsList = new ArrayList<Tags>();
        tagsList = tagsService.queryTagsByClassify(classifyId);
        return tagsList;
    }

    @RequestMapping("/toAddTagsOne")
    public ModelAndView toAddTagsOne(){

        ModelAndView model = new ModelAndView();
        model.setViewName("manage/add_tagsOne");
        return model;
    }

    @RequestMapping("/addTagsOne")
    @ResponseBody
    public Tags addTagsOne(String name,int classifyId){

        Tags tags = new Tags();
        tags.setName(name);
        List<Tags> tagsList = tagsService.queryTags(tags);//检查是否有重名

        if(tagsList.size() == 0){
            tags.setIsLeaf("1");
            int result = tagsService.saveTag(tags);
            if(result != 0){//保存成功，则保存关联表

                TagsRel tagsRel = new TagsRel();
                tagsRel.setTagId(tags.getId());
                tagsRel.setParentId(classifyId);
                result = tagsRelService.save(tagsRel)*result;
                if(result != 0)
                    return tags;
            }
            tags.setId(0);
            tags.setName("保存失败，请重试");
            return tags;
        }
        tags.setId(0);
        tags.setName("有重名，请修改");
        return tags;

    }

    @RequestMapping("/toEditorTagsOne")
    public ModelAndView toEditorTagsOne(int tagsOneId){

        List<Tags> classifyList = new ArrayList<Tags>();
        classifyList = tagsService.queryClassify();
        ModelAndView view = new ModelAndView();
        view.addObject("classifyList",classifyList);
        view.addObject("tagsOneId",tagsOneId);
        view.setViewName("manage/editor_tagsOne");
        return view;
    }

    @RequestMapping("/editorTagsOne")
    @ResponseBody
    public TagsRelExt editorTagsOne(int id, String name, int classifyId){

        TagsRelExt tagsRelExt = new TagsRelExt();

        Tags tags = new Tags();
        tags.setName(name);
        tags.setId(id);
        List<Tags> tagsList = tagsService.queryTags(tags);//检查是否有重名

        if(tagsList.size() == 0||(tagsList.get(0).getId() == id)){//没有重名或者跟自身标签重名

            int result = tagsService.saveTag(tags);
            if(result != 0){//保存成功，则保存关联表

                TagsRel tagsRel = new TagsRel();
                tagsRel.setTagId(tags.getId());
                List<TagsRel> tagsRelList = tagsRelService.queryTagsRel(tagsRel);//查找关联表中的记录
                if(tagsRelList.size()>0&&(tagsRelList.get(0).getParentId()!=classifyId)){//父节点若修改，则将修改结果保存到数据库

                    tagsRel.setId(tagsRelList.get(0).getId());
                    tagsRel.setParentId(classifyId);//修改父节点
                    result = tagsRelService.save(tagsRel)*result;
                }
                if(result != 0 ){

                    tagsRelExt.setTagId(id);
                    tagsRelExt.setParentId(classifyId);
                    tagsRelExt.setTagsName(name);
                    return tagsRelExt;
                }

            }else {
                tagsRelExt.setTagsName("保存失败，请重试");
            }

        }else{
            tagsRelExt.setTagsName("有重名，请修改");
        }
        tagsRelExt.setTagId(0);
        return tagsRelExt;

    }

    @RequestMapping("/toAddTagsTwo")
    public ModelAndView toAddTagsTwo(){

        ModelAndView model = new ModelAndView();
        List<TagsExt> tagsExtList = tagsService.queryClassifyAndChildren();
        model.addObject("tagsExtList",tagsExtList);
        model.setViewName("manage/add_tagsTwo");
        return model;
    }

    @RequestMapping(value = "/addTagsTwo", produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String  addTagsTwo(String name,String tagsOneIds){

        String[] tagsOneIdsArray = tagsOneIds.split(",");

        Tags tags = new Tags();
        tags.setName(name);
        List<Tags> tagsList = tagsService.queryTags(tags);//检查是否有重名

        if(tagsList.size() == 0){

            tags.setIsLeaf("1");
            int result = tagsService.saveTag(tags);

            for (String aTagsOneIdsArray : tagsOneIdsArray) {

                TagsRel tagsRel = new TagsRel();
                tagsRel.setTagId(tags.getId());
                tagsRel.setParentId(Integer.parseInt(aTagsOneIdsArray));
                result = tagsRelService.save(tagsRel);
            }
            if(result!=0)
                return "保存成功";
            else{
                return "保存失败";
            }

        }else {
            return "标签重名，请修改";
        }

    }

    @RequestMapping(value = "/toEditorTagsTwo",produces = {"application/text;charset=UTF-8"})
    public ModelAndView toEditorTagsTwo(int tagsTwoId){

        ModelAndView view = new ModelAndView();
        List<TagsExt> tagsExtList = tagsService.queryClassifyAndChildren();//所有分类以及所包含的一级标签
        view.addObject("tagsExtList",tagsExtList);
        List<Tags> parentList = tagsService.getParentsByTags(tagsTwoId);//该标签的所有父标签
        view.addObject("parentList",parentList);

        view.setViewName("manage/editor_tagsTwo");
        return view;
    }

    @RequestMapping(value = "/editorTagsTwo", produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String editorTagsTwo(int id,String name,String tagsOneIds){

        String[] tagsOneIdsArray = tagsOneIds.split(",");

        Tags tags = new Tags();
        tags.setName(name);
        List<Tags> tagsList = tagsService.queryTags(tags);//检查是否有重名

        if(tagsList.size() == 0||(tagsList.get(0).getId() == id)){//没有重名或者跟自身标签重名

            tags.setId(id);
            int result = tagsService.saveTag(tags);

            result = tagsRelService.deleteRelByTagId(id)*result;//删除原来的父标签
            for (String aTagsOneIdsArray : tagsOneIdsArray) {

                TagsRel tagsRel = new TagsRel();
                tagsRel.setTagId(id);
                tagsRel.setParentId(Integer.parseInt(aTagsOneIdsArray));
                result = tagsRelService.save(tagsRel);
            }
            if(result!=0)
                return "修改成功";
            else{
                return "修改失败";
            }

        }else {
            return "标签重名，请修改";
        }
    }

  


}
