package com.fw.domian;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author yqf
 * @date 2020 下午2:36
 */
@Component
public class Folder implements Serializable {
    private Integer id;
    private String title;
    private String category;
    private String updateTime;
    private Boolean status;
    private String introduction;
    private List<UploadFile> files;

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", status=" + status +
                ", introduction='" + introduction + '\'' +
                ", files=" + files +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<UploadFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadFile> files) {
        this.files = files;
    }
}
