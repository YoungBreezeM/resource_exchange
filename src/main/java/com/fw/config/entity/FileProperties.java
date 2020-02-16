package com.fw.config.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yqf
 * @date 2020 上午10:23
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    @Override
    public String toString() {
        return "FilesUpload{" +
                "uploadDir='" + uploadDir + '\'' +
                '}';
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
