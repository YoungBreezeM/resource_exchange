package com.fw.service;

import com.fw.domian.Result;
import com.fw.domian.UploadFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author yqf
 * @date 2020 上午11:10
 */
public interface FileService {
    //查找所有文件
    List<UploadFile> findAll();

    //通过id 查找文件
    UploadFile findUploadFileById(Integer id);

    //文件上传
    UploadFile storeFile(MultipartFile file,Integer folderId);

    //文件下载
    Resource loadFileAsResource(String fileName);

    //删除文件
    void deleteFile(Integer id);

}
