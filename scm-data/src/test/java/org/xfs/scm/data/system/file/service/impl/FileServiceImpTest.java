package org.xfs.scm.data.system.file.service.impl;

import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.system.file.service.FileServiceI;
import org.xfs.scm.data.system.file.vo.FileVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileServiceImpTest extends BaseTest {
    @Resource
    private FileServiceI fileService;

    @Test
    public void testAddFiles(){
                System.out.println(this.fileService+"-------------------");

//        List<FileVo> list=new ArrayList<>();
//        for(int i=1;i<=100;i++){
//            FileVo vo= new FileVo();
//            vo.setTableName("tb_user");
//            vo.setColumnName("headerUrl");
//            vo.setType("png");
//            vo.setState(1);
//            vo.setCreatedBy("系统");
//            vo.setLastUpdateBy("系统");
//            vo.setCreatedTime(new Date());
//            vo.setName("abcd:"+i);
//            vo.setLastUpdateTime(new Date());
//            vo.setKeyword(i+"");
//            vo.setUrl("cd");
//            list.add(vo);
//        }
//        System.out.println(this.fileService.addFileList(list));
    }
}
