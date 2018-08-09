package org.xfs.scm.data.business.crm.user_customer;

import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.crm.user_customer.service.UserCustomerServiceI;
import org.xfs.scm.data.business.crm.user_customer.vo.UserCustomerVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCustomerServiceImplTest extends BaseTest {

    @Resource
    private UserCustomerServiceI userCustomerService;

    @Test
    public void testBatchAdd(){
        List<UserCustomerVo> list=new ArrayList<>();
        for (int i=1;i<10;i++){
            UserCustomerVo vo=new UserCustomerVo();
            vo.setUserId(1L);
            vo.setCustId(Long.parseLong(i+""));
            vo.setState(1);
            vo.setCreatedBy("系统");
            vo.setCreatedTime(new Date());
            vo.setLastUpdateBy(vo.getCreatedBy());
            vo.setLastUpdateTime(vo.getCreatedTime());
            list.add(vo);
        }
        int count=this.userCustomerService.addUserCustomers(list);
        System.out.println("count:"+count);

    }
}
