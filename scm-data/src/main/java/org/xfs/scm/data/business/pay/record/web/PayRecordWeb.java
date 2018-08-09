package org.xfs.scm.data.business.pay.record.web;

import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/scm/pay/record/")
public class PayRecordWeb {

    @Resource
    private PayRecordServiceI payRecordService;

    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(PayRecordVo data){
        data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
        data.setCreatedTime(new Date());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setLastUpdateTime(data.getCreatedTime());
        if(this.payRecordService.add(data)==1){
            return JsonResponse.success("预付订单创建成功！");
        }else{
            return JsonResponse.fail("预付订单创建失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(Long id){
        PayRecordVo data =new PayRecordVo(id);
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.payRecordService.remove(data)==1){
            return JsonResponse.success("预付订单删除成功！");
        }else{
            return JsonResponse.fail("预付订单删除失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(PayRecordVo data){
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.payRecordService.modify(data)==1){
            return JsonResponse.success("预付订单修改成功！");
        }else{
            return JsonResponse.fail("预付订单修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        PayRecordPo payRecord=this.payRecordService.get(new PayRecordVo(id));
        if(payRecord!=null){
            return JsonResponse.success("查询成功！",payRecord);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(PayRecordVo data){
        Grid<PayRecordPo> grid=this.payRecordService.grid(data);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

}
