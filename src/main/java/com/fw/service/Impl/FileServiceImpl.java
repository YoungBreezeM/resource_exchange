package com.fw.service.Impl;

import com.fw.config.entity.FileProperties;
import com.fw.dao.FileDao;
import com.fw.domian.Result;
import com.fw.domian.UploadFile;
import com.fw.exception.FileException;
import com.fw.service.FileService;
import com.fw.utils.DateUtil;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * @author yqf
 * @date 2020 上午10:53
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;
    //导入文件上传路径配置
    @Autowired
    FileProperties fileProperties;

    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public FileServiceImpl(FileProperties fileProperties) throws FileNotFoundException {
        File classpath = new File(ResourceUtils.getURL("classpath:static").getPath());
        this.fileStorageLocation = Paths.get(
                classpath.getAbsolutePath()+fileProperties.getUploadDir()
        ).toAbsolutePath().normalize();//获取存储文件路径
        try {
            //如果文件目录不存在就创建目录
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public List<UploadFile> findAll() {
        return fileDao.findAll();
    }

    @Override
    public UploadFile findUploadFileById(Integer id) {
        return fileDao.findUploadFileById(id);
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public UploadFile storeFile(MultipartFile file,Integer folderId) {

        String fileName =new DateUtil().createTime("yyyy-MM-dd_HH:mm:ss") +"_"+ StringUtils.cleanPath(file.getOriginalFilename());
        String fileDownloadUrl = "/downloadFile/"+fileName;
        String fileAccessUrl = "/uploads/"+fileName;
        UploadFile uploadFile = new UploadFile(
                fileName,
                fileDownloadUrl,
                fileAccessUrl,
                file.getContentType(),
                file.getSize()
        );
        uploadFile.setFolderId(folderId);
        try {
            // 检查文件路径是否正确
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            //解析本地文件路径
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            //将图片拷贝到指定文件夹下
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            //记录文件信息
            fileDao.addFile(uploadFile);
            return uploadFile;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }

    /**
     * 删除文件
     * */
    public void deleteFile(Integer id){
        try {
            UploadFile uploadFile = fileDao.findUploadFileById(id);
            //如果记录为空
            if(uploadFile==null){
                throw new RuntimeSqlException("要删除记录为空:"+id);
            }
            String fileName = uploadFile.getFileName();
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            File file = new File(String.valueOf(filePath));
            //如果文件不存在
            if (!file.exists()) {
                //删除数据库冗余记录
                fileDao.deleteFile(id);
                throw new FileNotFoundException("文件无法找到"+fileName);
            }
            if(file.delete()){
                fileDao.deleteFile(id);
            }
            System.out.println("s");
        }catch (Exception e){
            System.out.println("在这里进行异常处理"+e.getMessage());
            throw new ExecutorException(e);
        }
    }
}

