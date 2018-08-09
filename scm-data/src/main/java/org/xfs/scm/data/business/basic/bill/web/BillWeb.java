package org.xfs.scm.data.business.basic.bill.web;

import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.basic.bill.entity.Bill;
import org.xfs.scm.data.business.basic.bill.service.BillServiceI;
import org.xfs.scm.data.business.basic.bill.vo.BillVo;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/rest/basic/bill/")
public class BillWeb {

    @Resource
    private BillServiceI billService;


    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(BillVo data){
        data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
        data.setCreatedTime(new Date());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setLastUpdateTime(data.getCreatedTime());
        if(this.billService.add(data)==1){
            return JsonResponse.success("货币添加成功！");
        }else{
            return JsonResponse.fail("货币添加失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(Long id){
        BillVo data=new BillVo(id);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.billService.remove(data)==1){
            return JsonResponse.success("货币删除成功！");
        }else{
            return JsonResponse.fail("货币删除失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(BillVo data){
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.billService.modify(data)==1){
            return JsonResponse.success("货币修改成功！");
        }else{
            return JsonResponse.fail("货币修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        Bill customer=this.billService.get(new BillVo(id));
        if(customer!=null){
            return JsonResponse.success("查询成功！",customer);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(BillVo data){
        Grid<Bill> grid=this.billService.grid(data);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

}
