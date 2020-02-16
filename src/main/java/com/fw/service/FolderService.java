package com.fw.service;

import com.fw.domian.Folder;

import java.util.List;

/**
 * @author yqf
 * @date 2020 下午5:52
 */

public interface FolderService {
    //获取文件夹列表
    List<Folder> findAll();

    //添加文件夹
    void addFolder(Folder folder);

    //通过id获取文件夹
    Folder findFolderById(Integer id);

    //更新文件夹
    void updateFolder(Folder folder);

    //删除文件夹
    void deleteFolder(Integer id);
}
