package org.xfs.scm.data.business.busi.log.service;

import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.busi.log.service.impl.BusiOrderLogServiceImpl;
import org.xfs.scm.data.business.busi.log.vo.BusiOrderLogVo;

import javax.annotation.Resource;
import java.util.Date;

public class BusiOrderLogServiceImplTest extends BaseTest{


    @Resource
    private BusiOrderLogServiceImpl service;


    @Test
    public void testAdd(){
        BusiOrderLogVo vo=new BusiOrderLogVo();

        vo.setCreateTime(new Date());
        vo.setOpMid(1L);
        vo.setOpType(1);
        vo.setOpUserId(1L);
        vo.setOrderId("333");
        vo.setOrderStatusFrom(0);
        vo.setOrderStatusTo(1);
        vo.setRemark("nothing to do!");
        System.out.println("====================="+this.service.save(vo));

    }

}
