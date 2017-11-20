package com.jamedow.laodoufang.entity;

import java.util.Date;

public class VoteLog {
    private Integer id;
    private Integer objId;
    private Integer status;
    private Integer userId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public enum VoteStatus {
        UP_VOTE(1, "赞"), UN_VOTE(0, "未记录"), DOWN_VOTE(-1, "踩");
        /**
         * resultCode小于-10000为异常编码，大于10000为正常编码
         */
        private final int status;
        /**
         * message
         */
        private final String name;

        VoteStatus(int status, String name) {
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }
    }
}