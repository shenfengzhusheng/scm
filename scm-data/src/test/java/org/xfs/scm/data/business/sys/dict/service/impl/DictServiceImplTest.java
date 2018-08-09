package org.xfs.scm.data.business.sys.dict.service.impl;

import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.basic.dict.service.DictServiceI;
import org.xfs.scm.data.business.basic.dict.vo.DictInfoVo;
import org.xfs.scm.data.business.basic.dict.vo.DictVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/12 0012.22:01
 * version:1.0
 */
public class DictServiceImplTest extends BaseTest {

    @Resource
    private DictServiceI service;

    @Test
    public void doAddTest(){
        //System.out.println("------------------"+this.service);

        for(int i=0;i<12;i++){
            DictVo vo=new DictVo();
            vo.setCreatedBy(super.userName);
            vo.setLastUpdateBy(super.userName);
            vo.setCreatedTime(new java.util.Date());
            vo.setDictCode("3code"+(i+1));
            vo.setDictName("3名称"+(i+1));
            vo.setDictTypeCode("003");
            vo.setTypeSeq(3);
            vo.setDictTypeName("类型二");
            vo.setSeq((i+1));
            this.service.addDictPo(vo );
        }
    }

    @Test
    public void testGetDictInfo(){
        List<DictInfoVo> list=this.service.getDictInfo(null);
        if(list!=null && list.size()>0){
            System.out.println(list.size());
            System.out.println(list.get(2).getDictSets().size());

        }
    }
}
