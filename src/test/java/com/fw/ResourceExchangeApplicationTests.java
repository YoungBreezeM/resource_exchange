package com.fw;

import com.fw.dao.FileDao;
import com.fw.domian.UploadFile;
import com.fw.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ResourceExchangeApplicationTests {

    @Autowired
    FileDao fileDao;

    public UploadFile uploadFile(Integer id){
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFolderId(id);
        return uploadFile;
    }

    @Test
    void contextLoads() {
        fileDao.findFileByFolderId(9);
    }

}
