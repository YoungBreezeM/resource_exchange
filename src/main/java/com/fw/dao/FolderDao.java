package com.fw.dao;

import com.fw.domian.Folder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 * @date 2020 下午4:08
 */
@Repository
public interface FolderDao {

    /**
     * 查找所有文件夹信息
     * */
    @Select("select * from folder")
    @Results(id = "folderMap",value = {
            @Result(column = "update_time",property = "updateTime")
    })
    List<Folder> findAll();


    /**
     * 查找文件夹信息
     * 在文件夹查找图片集合
     * */
    @Select("select * from folder where id=#{id}")
    @Results({
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "id",property = "id"),
            @Result(property = "files",column = "id",
                    many = @Many(select = "com.fw.dao.FileDao.findFileByFolderId"))
    })
    Folder findFolderById(@Param("id") Integer id);

    /**
     * 添加文件夹
     * */
    @Insert("insert into folder(title,category,status,update_time,introduction) values(#{title},#{category},#{status},#{updateTime},#{introduction})")
    void addFolder(Folder folder);

    /**
     * 更新文件夹信息
     * */
    @Update("update folder set title=#{title},category=#{category},status=#{status},update_time=#{updateTime},introduction=#{introduction} where id = #{id}")
    void updateFolder(Folder folder);

    /**
     * 删除文件夹
     * */
    @Delete("delete from folder where id=#{id}")
    void deleteFolder(@Param("id") Integer id);



}
