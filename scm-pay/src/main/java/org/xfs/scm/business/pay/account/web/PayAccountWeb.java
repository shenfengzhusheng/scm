package org.xfs.scm.business.pay.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.business.pay.account.entity.PayAccount;
import org.xfs.scm.business.pay.account.service.PayAccountServiceI;
import org.xfs.scm.business.pay.account.vo.PayAccountVo;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;

import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/pay/account/")
public class PayAccountWeb extends BaseWeb{

    @Resource
    private PayAccountServiceI payAccountService;


    @ResponseBody
    @RequestMapping("/grid.do")
    public Object grid(PayAccountVo data){
        Grid<PayAccount> grid=this.payAccountService.grid(data);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }



}
