package com.fw.domian;

/**
 * @author yqf
 * @date 2020 上午10:45
 */
public class UploadFile {
    private Integer id;
    private String fileName;
    private String fileDownloadUrl;
    private String fileAccessUrl;
    private String fileType;
    private long size;
    private Integer folderId;

    public UploadFile(String fileName, String fileDownloadUrl, String fileAccessUrl, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUrl = fileDownloadUrl;
        this.fileAccessUrl = fileAccessUrl;
        this.fileType = fileType;
        this.size = size;
    }

    public UploadFile() {
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileDownloadUrl='" + fileDownloadUrl + '\'' +
                ", fileAccessUrl='" + fileAccessUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                ", size=" + size +
                ", folderId=" + folderId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }

    public String getFileAccessUrl() {
        return fileAccessUrl;
    }

    public void setFileAccessUrl(String fileAccessUrl) {
        this.fileAccessUrl = fileAccessUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

}
