package com.jamedow.laodoufang.service;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/11/17.
 */
public interface VoteService {
    long vote(int status, int objId, int userId);

    long getVoteCount(int objId, int status);

    int getVoteStatus(int objId, int userId);
}
