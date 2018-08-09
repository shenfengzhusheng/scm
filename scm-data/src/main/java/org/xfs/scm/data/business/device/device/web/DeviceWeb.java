package org.xfs.scm.data.business.device.device.web;

import org.springframework.web.bind.annotation.*;
import org.xfs.api.enums.DeviceStatusEnum;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.device.device.po.DevicePo;
import org.xfs.scm.data.business.device.device.service.DeviceServiceI;
import org.xfs.scm.data.business.device.device.vo.DeviceVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/rest/device/device/")
public class DeviceWeb extends BaseWeb {
    @Resource
    private DeviceServiceI deviceService;
    private String key;
//    @PostConstruct
//    public void init(){
//        key="456789";
//        System.out.println("--------PostConstruct---DeviceWeb------init------------->"+key);
//    }
    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(DeviceVo data){
        data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
        data.setCreatedTime(new Date());
        data.setDeviceState(DeviceStatusEnum.NORMAL_STATUS.getCode());
        data.setCustId(TokenManager.getCurrentUser().getCustId());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setLastUpdateTime(data.getCreatedTime());
        if(this.deviceService.add(data)==1){
            return JsonResponse.success("设备添加成功！");
        }else{
            return JsonResponse.fail("设备添加失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(Long id){
        DeviceVo data =new DeviceVo(id);
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.deviceService.remove(data)==1){
            return JsonResponse.success("设备删除成功！");
        }else{
            return JsonResponse.fail("设备删除失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(DeviceVo data){
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.deviceService.modify(data)==1){
            return JsonResponse.success("设备修改成功！");
        }else{
            return JsonResponse.fail("设备修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        DevicePo customer=this.deviceService.get(new DeviceVo(id));
        if(customer!=null){
            return JsonResponse.success("查询成功！",customer);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(DeviceVo data){
        Grid<DevicePo> grid=this.deviceService.grid(data);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

}
