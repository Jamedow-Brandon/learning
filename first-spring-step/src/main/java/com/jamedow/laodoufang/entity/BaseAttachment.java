package com.jamedow.laodoufang.entity;

public class BaseAttachment {
    private Integer id;

    private Long resourceId;

    private String resourceType;

    private Integer bizType;

    private String name;

    private Integer attachmentTypeId;

    private String suffix;

    private long size;

    private String remotePath;

    private Integer remoteServerUrliid;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttachmentTypeId() {
        return attachmentTypeId;
    }

    public void setAttachmentTypeId(Integer attachmentTypeId) {
        this.attachmentTypeId = attachmentTypeId;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public Integer getRemoteServerUrliid() {
        return remoteServerUrliid;
    }

    public void setRemoteServerUrliid(Integer remoteServerUrliid) {
        this.remoteServerUrliid = remoteServerUrliid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}