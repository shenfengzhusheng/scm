package org.xfs.scm.data.business.busi.contact.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.busi.contact.service.impl.BusiOrderContactServiceImpl;
import org.xfs.scm.data.business.busi.contact.vo.BusiOrderContactVo;

import java.util.List;

public class BusiOrderContactServiceImplTest extends BaseTest {

    @Autowired
    private BusiOrderContactServiceImpl service;

    @Test
    public void testAdd(){
        BusiOrderContactVo vo=new BusiOrderContactVo();
        vo.setOrderId("333");
        vo.setEndAddrCode("120000:天津市;120100:市辖区;120101:和平区");
        vo.setEndAddrDetail("滨海新区");
        vo.setStartAddrCode("110000:北京市;110100:市辖区;110101:东城区");
        vo.setStartAddrDetail("海淀区");
        vo.setStartContactName("林晓茹");
        vo.setStartContactPhone("18860052855");
        vo.setEndContactPhone("18860052855");
        vo.setEndContactName("林晓茹");
        System.out.println("result is:"+this.service.save(vo));
        System.out.println(vo.getId()+":         ");
    }

  //  @Test
    public void testQuery(){
        BusiOrderContactVo vo=new BusiOrderContactVo();
        vo.setOrderId("333");
        List<BusiOrderContactVo> list=this.service.queryListByWhere(vo);
        for(BusiOrderContactVo bv :list){
            System.out.println(bv);
        }
    }

}
