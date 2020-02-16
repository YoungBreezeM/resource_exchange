package com.fw.controller;

import com.alibaba.fastjson.JSONException;
import com.fw.domian.Folder;
import com.fw.domian.Result;
import com.fw.domian.UploadFile;
import com.fw.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yqf
 * @date 2020 上午10:43
 */
@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    FileService fileService;

    /**
     * 单文件上传
     * */
    @PostMapping("/uploadFile/{folderId}")
    public UploadFile uploadFile(@RequestParam("file") MultipartFile file,@PathVariable Integer folderId){

//        String fileName =
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//        String fileAccessUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/uploads/")
//                .path(fileName)
//                .toUriString();
        return fileService.storeFile(file,folderId);
    }
    /**
     *多文件上传
     * 基于单文件上传,通过遍历上传
     * */
    @PostMapping("/uploadMultipleFiles/{folderId}")
    public List<UploadFile> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@PathVariable Integer folderId) {
        return Arrays.stream(files)
                .map(file->uploadFile(file,folderId))
                .collect(Collectors.toList());
    }

    /**
     * 下载文件
     * */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // 设置文件传输方式
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * 文件删除
     * */
    @DeleteMapping("deleteFile/{id}")
    public Result deleteFile(@PathVariable Integer id){
        try {
            fileService.deleteFile(id);
            return new Result(0,"success",null);
        }catch (Exception e){
            return new Result(1,e.getMessage(),null);
        }
    }

    /**
     * 文件批量删除
     * */
    @DeleteMapping("deleteFiles")
    public Result deleteFiles(@RequestBody Folder folder) throws JSONException {
        try {
            List<UploadFile> uploadFiles = folder.getFiles();
            System.out.println(uploadFiles);
            for(UploadFile file:uploadFiles){
                fileService.deleteFile(file.getId());
            }
            return new Result(0,"success",null);
        }catch (Exception e){
            return new Result(1,e.getMessage(),null);
        }

    }


}
