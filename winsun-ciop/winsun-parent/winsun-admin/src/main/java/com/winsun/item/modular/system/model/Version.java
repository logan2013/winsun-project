package com.winsun.item.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
@TableName("app_version")
public class Version {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String version;

    private String description;

    private Date updateTime;

    private String softSystem;

    private String appUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSoftSystem() {
        return softSystem;
    }

    public void setSoftSystem(String softSystem) {
        this.softSystem = softSystem;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", updateTime=" + updateTime +
                ", softSystem='" + softSystem + '\'' +
                ", appUrl='" + appUrl + '\'' +
                '}';
    }
}
