package com.fw.dao;

import com.fw.domian.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author yqf
 * @date 2020 下午4:08
 */
@Repository
public interface FileDao {
    /**
     * 查找所有文件
     * */
    @Select("select * from file")
    @Results(id = "fileMap",value = {
            @Result(column = "folder_id",property = "folderId")
    })
    List<UploadFile> findAll();

    /**
     * 通过文件夹id 查找所有文件
     * */
    @Select("select * from file where folder_id =#{folderId}")
    @Results(id = "filesMap",value = {
            @Result(column = "folder_id",property = "folderId")
    })
    List<UploadFile> findFileByFolderId(@Param("folderId") Integer folderId);

    @Select("select * from file where id =#{id}")
    UploadFile findUploadFileById(@Param("id") Integer id);


    /**
     * 添加文件
     * */
    @Insert("insert into file (fileName,fileDownloadUrl,fileAccessUrl,size,fileType,folder_id) values(#{fileName},#{fileDownloadUrl},#{fileAccessUrl},#{size},#{fileType},#{folderId})")
    void addFile(UploadFile uploadFile);

    /**
     * 删除id
     * */
    @Delete("delete from file where id =#{id}")
    void deleteFile(@Param("id") Integer id);
}
