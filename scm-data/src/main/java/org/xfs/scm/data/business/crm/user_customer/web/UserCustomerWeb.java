package org.xfs.scm.data.business.crm.user_customer.web;

import jdk.nashorn.internal.parser.Token;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.user_customer.entity.UserCustomer;
import org.xfs.scm.data.business.crm.user_customer.service.UserCustomerServiceI;
import org.xfs.scm.data.business.crm.user_customer.vo.GrantUserCustomerVo;
import org.xfs.scm.data.business.crm.user_customer.vo.UserCustomerVo;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/crm/user-cust/")
public class UserCustomerWeb  {

    @Resource
    private UserCustomerServiceI userCustomerService;

    @RequestMapping(value = "getUserCustomers.do/{userId}",method = RequestMethod.GET)
    public Object getUserCustomers(@PathVariable Long userId){
        UserCustomerVo data=new UserCustomerVo();
        data.setUserId(userId);
       // data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        List<UserCustomer> list=this.userCustomerService.find(data);
        if(!list.isEmpty()){
            return JsonResponse.success("获取成功！",list);
        }else{
            return JsonResponse.success("获取成功！",new ArrayList<UserCustomer>());

        }
    }

    @RequestMapping(value = "grant.do",method = RequestMethod.POST)
    public Object grant(@Valid GrantUserCustomerVo data){
        data.setUserName(TokenManager.getCurrentUser().getUserName());
        data.setDate(new Date());
        this.userCustomerService.grantUserCustomer(data);
        return JsonResponse.success("授权成功！");

    }


}
