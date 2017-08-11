package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.entity.BaseAttachment;
import com.jamedow.laodoufang.service.BaseAttachmentService;
import com.jamedow.laodoufang.utils.FileLocationAttribute;
import com.jamedow.laodoufang.utils.ftp.FTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/4.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    /**
     * 最大上传文件大小
     */
    private static final long MAX_SIZE_OF_UPLOAD_FILE = 10485760;
    @Autowired
    private BaseAttachmentService attachmentService;
    @Autowired
    private FTPUtils ftpUtils;

    /**
     * 功能描述: <br>
     * 保存附件
     *
     * @param request
     * @author chengjianfang
     * @version [v1.0.0, 2015年1月29日]
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/addSection", method = {RequestMethod.POST})
    @ResponseBody
    public String addSection(@RequestParam(value = "upload", required = false) MultipartFile file, String resourceType) {
        List<BaseAttachment> attachments = new ArrayList<>();
        logger.debug("==addSection==start=======");
        String remotePath = "";
        try {
            logger.debug("==addSection==fileName:{}==", file.getOriginalFilename());
            logger.debug("===>>fileType:{}>>===", file.getContentType());
            BaseAttachment attachment = new BaseAttachment();

            FileLocationAttribute fileLocationAttribute = new FileLocationAttribute(file);
            long fileSize = fileLocationAttribute.getSize();
            if (fileSize > MAX_SIZE_OF_UPLOAD_FILE) {
                return "MAX_SIZE";
            }
            attachment.setName(file.getOriginalFilename());
            attachment.setResourceType(resourceType);
            attachment.setSize(fileSize);
            attachment.setSuffix(fileLocationAttribute.getSuffix());
            remotePath = ftpUtils.uploadFile(fileLocationAttribute
                    .getStoreLocation().getPath(), "media/recipe/detail", file.getOriginalFilename());
            attachment.setRemotePath(remotePath);

            attachments.add(attachment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return remotePath;
    }

    /**
     * 功能描述: <br>
     * 保存附件
     *
     * @param request
     * @author chengjianfang
     * @version [v1.0.0, 2015年1月29日]
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/addSectionForCKEditor", method = {RequestMethod.POST})
    @ResponseBody
    public String addSectionForCKEditor(@RequestParam(value = "upload", required = false) MultipartFile file,
                                        String CKEditorFuncNum, String resourceType) {
        List<BaseAttachment> attachments = new ArrayList<>();
        logger.debug("==addSection==start=======");
        String remotePath = "";
        try {
            logger.debug("==addSection==fileName:{}==", file.getOriginalFilename());
            logger.debug("===>>fileType:{}>>===", file.getContentType());
            BaseAttachment attachment = new BaseAttachment();

            FileLocationAttribute fileLocationAttribute = new FileLocationAttribute(file);
            long fileSize = fileLocationAttribute.getSize();
            if (fileSize > MAX_SIZE_OF_UPLOAD_FILE) {
                return "MAX_SIZE";
            }
            attachment.setName(file.getOriginalFilename());
            attachment.setResourceType(resourceType);
            attachment.setSize(fileSize);
            attachment.setSuffix(fileLocationAttribute.getSuffix());
            remotePath = ftpUtils.uploadFile(fileLocationAttribute
                    .getStoreLocation().getPath(), "media/recipe/detail", file.getOriginalFilename());
            attachment.setRemotePath(remotePath);

            attachments.add(attachment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // 返回“图像”选项卡并显示图片
        StringBuilder content = new StringBuilder();
        content.append("<script type=\"text/javascript\">");
        content.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
                + ",'" + remotePath + "','')");
        content.append("</script>");
        return content.toString();
    }

    /**
     * 功能描述: <br>
     * 删除附件
     *
     * @param id 附件id
     */
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object deleteAttachment(@PathVariable(value = "id") Integer id) {
        return attachmentService.deleteBaseAttachment(id);
    }
}
