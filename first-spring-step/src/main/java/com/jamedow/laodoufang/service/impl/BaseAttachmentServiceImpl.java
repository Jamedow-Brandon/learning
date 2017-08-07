package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.BaseAttachment;
import com.jamedow.laodoufang.mapper.BaseAttachmentMapper;
import com.jamedow.laodoufang.service.BaseAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 365 on 2016/12/12 0012.
 */
@Service
public class BaseAttachmentServiceImpl implements BaseAttachmentService {
    @Autowired
    private BaseAttachmentMapper attachmentMapper;

    @Override
    public int saveBaseAttachment(BaseAttachment baseAttachment) {
        return attachmentMapper.insert(baseAttachment);
    }

    @Override
    public int deleteBaseAttachment(Integer id) {
        return attachmentMapper.deleteByPrimaryKey(id);
    }
}
