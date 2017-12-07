package com.jamedow.laodoufang.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

public class FileLocationAttribute {
    /**
     * 磁盘文件
     */
    private DiskFileItem diskFileItem;
    /**
     * 表单属性名
     */
    private String fieldName;
    /**
     * 展示路径
     */
    private String realPath;
    /**
     * 文件名
     */
    private String realName;
    /**
     * 文件对象
     */
    private File storeLocation;
    /**
     * 文件大小，单位:字节
     */
    private long size;
    /**
     * 文件内容
     */
    private byte[] content;
    /**
     * 内容类型
     */
    private String contentType;
    /**
     * 后缀
     */
    private String suffix;

    public FileLocationAttribute(MultipartFile file) throws IOException {
        if (file instanceof CommonsMultipartFile) {
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
            commonsMultipartFile.getStorageDescription();
            FileItem fileItem = commonsMultipartFile.getFileItem();
            if (fileItem instanceof DiskFileItem) {
                this.diskFileItem = (DiskFileItem) fileItem;
                String fullName = diskFileItem.getName();
                int index = fullName.lastIndexOf("/");
                if (-1 == index) {
                    index = fullName.lastIndexOf("\\");
                }
                if (-1 == index) {
                    this.realPath = fullName;
                } else {
                    this.realPath = fullName.substring(0, index);
                }
                this.storeLocation = diskFileItem.getStoreLocation();
            }
        }
        this.realName = file.getOriginalFilename();
        this.suffix = realName.substring(realName.lastIndexOf('.') + 1);
        this.fieldName = file.getName();
        this.size = file.getSize();
        this.content = file.getBytes();
        this.contentType = file.getContentType();
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @return the realPath
     */
    public String getRealPath() {
        return realPath;
    }

    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @return the storeLocation
     */
    public File getStoreLocation() {
        return storeLocation;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @return the content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 文件写入磁盘
     *
     * @param destFile
     * @throws Exception
     */
    public void writeToFile(File destFile) throws Exception {
        if (null != diskFileItem) {
            this.diskFileItem.write(destFile);
        } else {
            throw new RuntimeException("文件没有找到,无法写入");
        }
    }

    /**
     * @return the suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("contentType", this.contentType).append("realPath", this.realPath)
                .append("realName", this.realName).append("size", this.size)
                .append("storeLocation", this.storeLocation).append("fieldName", this.fieldName).toString();
    }

}
