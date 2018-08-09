package org.xfs.scm.data.business.busi.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.busi.order.service.BusiOrderServiceI;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderVo;
import org.xfs.scm.data.business.enums.OrderBidTypeEnum;
import org.xfs.scm.data.business.enums.OrderSrcEnum;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/busi/order/")
public class BusiOrderWeb extends BaseWeb {

    @Resource
    private BusiOrderServiceI service;

    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid( BusiOrderVo vo,Integer page,Integer rows){
        Grid<BusiOrderVo> grid=this.service.grid(vo,page,rows);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.fail("没有查到数据！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "addOrderBidByPlateForm",method = RequestMethod.POST)
    public Object addOrderBidByPlateForm( BusiOrderVo vo){
        vo.setBidType(OrderBidTypeEnum.FAMILY_BID.getCode());

        vo.setOwnerUserId(-99L);
        vo.setOrderSrc(OrderSrcEnum.PC.getCode());
        vo.setCreateTime(new Date());
        vo.setUpdateTime(vo.getCreateTime());
        this.service.addBusiOrderVo(vo);
        if(vo!=null && vo.getId()!=null){
            return JsonResponse.success("下单成功！");
        }else{
            return JsonResponse.fail("下单失败！");
        }
    }



}


