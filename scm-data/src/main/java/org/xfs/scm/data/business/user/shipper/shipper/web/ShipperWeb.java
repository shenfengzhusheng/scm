package org.xfs.scm.data.business.user.shipper.shipper.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.service.ShipperServiceI;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user/shipper/")
public class ShipperWeb {

    @Resource
    private ShipperServiceI shipperService;

    @ResponseBody
    @RequestMapping(value =  "info/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        if(id!=null){
            ShipperVo shipperVo=new ShipperVo();
            shipperVo.setUserId(id);
            ShipperBo shipperBo=this.shipperService.getShipper(shipperVo);
            if(shipperBo!=null){
                return JsonResponse.success("获取成功！",shipperBo);
            }
        }else{
            return JsonResponse.fail("传值异常！");
        }
        return JsonResponse.fail("查询异常！");
    }

}
