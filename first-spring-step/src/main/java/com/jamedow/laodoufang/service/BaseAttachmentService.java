package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.BaseAttachment;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface BaseAttachmentService {

    int saveBaseAttachment(BaseAttachment baseAttachment);

    int deleteBaseAttachment(Integer id);
}
