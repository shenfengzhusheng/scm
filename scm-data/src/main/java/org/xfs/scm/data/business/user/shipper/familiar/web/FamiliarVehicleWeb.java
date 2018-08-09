package org.xfs.scm.data.business.user.shipper.familiar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.user.shipper.familiar.service.FamiliarVehicleServiceI;
import org.xfs.scm.data.business.user.shipper.familiar.vo.FamiliarVehicleVo;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user/shipper/familiarvehicle/")
public class FamiliarVehicleWeb extends BaseWeb{

    @Resource
    private FamiliarVehicleServiceI familiarVehicleService;

    @ResponseBody
    @RequestMapping(value = "getUserFamiliarVehicle",method = RequestMethod.GET)
    public Object getUserFamiliarVehicle(FamiliarVehicleVo vo){
        vo.setOwnerUserId(-99L);
        List<FamiliarVehicleVo> list=this.familiarVehicleService.getFamiliarVehicles(vo);
        if(!list.isEmpty()){
            return JsonResponse.success("获取成功!",list);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @RequestMapping("info/{id}")
    @ResponseBody
    public Object info(@PathVariable Long id){
        if(id!=null){
            FamiliarVehicleVo familiarVehicleVo=this.familiarVehicleService.getFamiliarVehicle(new FamiliarVehicleVo(id));
            if(familiarVehicleVo!=null){
              return  JsonResponse.success("查询成功！",familiarVehicleVo);
            }
        }
        return JsonResponse.fail("查询不到数据!");
    }

    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid(FamiliarVehicleVo data,Integer page,Integer rows){
         Grid<FamiliarVehicleVo> grid=this.familiarVehicleService.grid(data,page,rows);
         if(grid!=null){
             return JsonResponse.success("查询成功！",grid);
         }else{
             return JsonResponse.fail("查询失败!");
         }
    }

    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Object save(FamiliarVehicleVo data){
        if(data!=null){
            if(this.familiarVehicleService.addFamiliarVehicle(data)){
                return JsonResponse.success("添加成功！",data);
            }
        }
        return JsonResponse.fail("添加熟车失败！");
    }

    @ResponseBody
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    public Object remove(@Valid Long id){
        if(id!=null){
            FamiliarVehicleVo familiarVehicleVo=new FamiliarVehicleVo();
            familiarVehicleVo.setId(id);
            if(this.familiarVehicleService.removeFamiliarVehicle(familiarVehicleVo)==1){
                return JsonResponse.success("删除成功！",null);
            }
        }
        return JsonResponse.fail("删除熟车失败！");
    }



    @ResponseBody
    @RequestMapping(value = "modfiy",method = RequestMethod.POST)
    public Object modfiy(FamiliarVehicleVo data){
        if(data!=null){
            if(this.familiarVehicleService.modifyFamiliarVehicle(data)){
                return JsonResponse.success("修改成功！",null);
            }
        }
        return JsonResponse.fail("修改熟车失败！");
    }
}
