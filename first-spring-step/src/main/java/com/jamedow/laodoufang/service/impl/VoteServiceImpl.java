package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.VoteLog;
import com.jamedow.laodoufang.entity.VoteLogExample;
import com.jamedow.laodoufang.mapper.VoteLogMapper;
import com.jamedow.laodoufang.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
public class VoteServiceImpl implements VoteService {
    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);


    @Autowired
    private VoteLogMapper voteLogMapper;

    public int vote(int status, int objId, int userId) {
        VoteLogExample example = new VoteLogExample();
        example.createCriteria().andObjIdEqualTo(objId).andUserIdEqualTo(userId);
        List<VoteLog> votes = voteLogMapper.selectByExample(example);
        if (votes != null && votes.size() != 0) {
            VoteLog voteLog = votes.get(0);
            voteLog.setStatus(status);
            voteLog.setUpdateTime(new Date());
            return voteLogMapper.updateByPrimaryKey(voteLog);
        } else {
            VoteLog voteLog = new VoteLog();
            voteLog.setObjId(objId);
            voteLog.setUserId(userId);
            voteLog.setStatus(status);
            voteLog.setCreateTime(new Date());
            voteLog.setUpdateTime(new Date());
            return voteLogMapper.insert(voteLog);
        }
    }
}
