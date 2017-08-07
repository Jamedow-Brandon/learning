package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.entity.BaseAttachment;
import com.jamedow.laodoufang.service.BaseAttachmentService;
import com.jamedow.laodoufang.utils.FileLocationAttribute;
import com.jamedow.laodoufang.utils.ftp.FTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/4.
 */
@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    private BaseAttachmentService attachmentService;

    /**
     * 功能描述: <br>
     * 保存附件
     *
     * @param request
     * @param attachment
     */
    @RequestMapping(value = "attachmentAddSection", method = {RequestMethod.POST})
    @ResponseBody
    public void sampleAttachmentAddSection(MultipartHttpServletRequest request, BaseAttachment attachment) {
        try {
            MultipartFile file = request.getFile("file");
            String name = request.getParameter("name");
            String remark = request.getParameter("remark");
            int bizType = Integer.valueOf(request.getParameter("bizType"));
            try {
                FileLocationAttribute fileLocationAttribute = new FileLocationAttribute(file);
                attachment.setName(name);
                attachment.setRemark(remark);
                attachment.setBiztype(bizType);
                attachment.setSize(fileLocationAttribute.getSize());
                attachment.setSuffix(fileLocationAttribute.getSuffix());
                String remotePath = FTPUtils.uploadFile(fileLocationAttribute
                        .getStoreLocation().getPath(), "recipe/center");
                attachment.setRemotepath(remotePath);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            attachmentService.saveBaseAttachment(attachment);
        } catch (Exception se) {
            se.getStackTrace();
        }
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
