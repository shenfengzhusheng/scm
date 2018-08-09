package org.xfs.scm.data.system.payaccount.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.data.system.payaccount.vo.PayAccountVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/sys/pay/account/")
public class PayAccountWeb extends BaseWeb{

    @Resource
    private PayAccountServiceI payAccountService;
    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save( PayAccountVo data){
        data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
        data.setCreatedTime(new Date());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setLastUpdateTime(data.getCreatedTime());
        if(this.payAccountService.add(data)==1){
            return JsonResponse.success("支付帐号创建成功！");
        }else{
            return JsonResponse.fail("支付帐号创建失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(Integer id){
        PayAccountVo data =new  PayAccountVo(id);
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.payAccountService.remove(data)==1){
            return JsonResponse.success("支付帐号删除成功！");
        }else{
            return JsonResponse.fail("支付帐号删除失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify( PayAccountVo data){
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.payAccountService.modify(data)==1){
            return JsonResponse.success("支付帐号修改成功！");
        }else{
            return JsonResponse.fail("支付帐号修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Integer id){
        PayAccountVo data=new PayAccountVo(id);
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        PayAccountPo payRecord=this.payAccountService.get(data);
        if(payRecord!=null){
            return JsonResponse.success("查询成功！",payRecord);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @RequestMapping("/grid.do")
    public Object grid(PayAccountVo data){
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        Grid<PayAccountPo> grid=this.payAccountService.grid(data);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }





}
