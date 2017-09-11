package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/4.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 功能描述: <br>
     * 删除评论
     *
     * @param id 评论id
     */
    @RequestMapping(value = "/recipeComment/comments/{recipeId}", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object getComment(@PathVariable(value = "recipeId") Integer recipeId) {
        return commentService.getRecipeComments(recipeId);
    }

    /**
     * 功能描述: <br>
     * 删除评论
     *
     * @param id 评论id
     */
    @RequestMapping(value = "/recipeComment/delete/{id}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object deleteRecipeCommentById(@PathVariable(value = "id") Integer id) {
        return commentService.deleteRecipeCommentById(id);
    }
}
