package com.fw.service.Impl;

import com.fw.dao.FolderDao;
import com.fw.domian.Folder;
import com.fw.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 * @date 2020 下午5:54
 */
@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderDao folderDao;

    @Override
    public List<Folder> findAll() {
        return folderDao.findAll();
    }

    @Override
    public void addFolder(Folder folder) {
        folderDao.addFolder(folder);
    }

    @Override
    public Folder findFolderById(Integer id) {
        return folderDao.findFolderById(id);
    }

    @Override
    public void updateFolder(Folder folder) {
        folderDao.updateFolder(folder);
    }

    @Override
    public void deleteFolder(Integer id) {
        folderDao.deleteFolder(id);
    }
}
