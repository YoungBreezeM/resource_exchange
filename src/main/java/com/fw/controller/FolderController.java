package com.fw.controller;

import com.fw.domian.Folder;
import com.fw.domian.Result;
import com.fw.service.FolderService;
import com.fw.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @author yqf
 * @date 2020 下午5:50
 */
@RestController
@RequestMapping("/folder")
public class FolderController {
    private static final Logger logger = LoggerFactory.getLogger(Folder.class);
    @Autowired
    private FolderService folderService;
    /**
     * 获取文件夹列表
     * */
    @GetMapping
    public List<Folder> getFolderList(){
        return folderService.findAll();
    }

    /**
     * 添加文件夹
     * */
    @PostMapping
    public Result addFolder(@RequestBody Folder folder){
        folder.setUpdateTime(new DateUtil().createTime("yyyy-MM-dd HH:mm:ss"));
        folderService.addFolder(folder);
        return new Result(0,"success",getFolderList());
    }

    /**
     * 获取文件夹对象
     * */
    @GetMapping("{id}")
    public Folder getFolder(@PathVariable Integer id){
        return folderService.findFolderById(id);
    }

    /**
     * 更新文件夹
     * */
    @PutMapping
    public Result updateFolder(@RequestBody Folder folder){
        folder.setUpdateTime(new DateUtil().createTime("yyyy-MM-dd HH:mm:ss"));
        folderService.updateFolder(folder);
        return new Result(0,"success",getFolderList());
    }

    /**
     * 删除文件夹
     * */
    @DeleteMapping("{id}")
    public Result deleteFolder(@PathVariable Integer id){
        folderService.deleteFolder(id);
        return new Result(0,"success",getFolderList());
    }

}
