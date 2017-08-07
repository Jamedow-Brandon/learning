package com.jamedow.laodoufang.entity;

public class BaseAttachment {
    private Integer id;

    private Integer resourceid;

    private String resourcetype;

    private Integer biztype;

    private String name;

    private Integer attachmenttypeid;

    private String suffix;

    private Long size;

    private String remotepath;

    private Integer remoteserverurlid;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public Integer getBiztype() {
        return biztype;
    }

    public void setBiztype(Integer biztype) {
        this.biztype = biztype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttachmenttypeid() {
        return attachmenttypeid;
    }

    public void setAttachmenttypeid(Integer attachmenttypeid) {
        this.attachmenttypeid = attachmenttypeid;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getRemotepath() {
        return remotepath;
    }

    public void setRemotepath(String remotepath) {
        this.remotepath = remotepath;
    }

    public Integer getRemoteserverurlid() {
        return remoteserverurlid;
    }

    public void setRemoteserverurlid(Integer remoteserverurlid) {
        this.remoteserverurlid = remoteserverurlid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}