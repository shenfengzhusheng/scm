package org.xfs.scm.data.business.user.shipper.contact.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.user.shipper.contact.service.BusiContactServiceI;
import org.xfs.scm.data.business.user.shipper.contact.vo.BusiContactVo;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by 神风逐胜 on 2018/1/27 0027.21:50
 * version:1.0
 */
@Controller
@RequestMapping("/shipper/contact/")
public class BusiContactWeb extends BaseWeb {

    @Resource
    private BusiContactServiceI busiContactService;

    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid(BusiContactVo data,int page,int rows){
        Grid<BusiContactVo> grid=this.busiContactService.grid(data,page,rows);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.success("未查到相关信息！",grid);
        }
    }
    @ResponseBody
    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable Long id){
        BusiContactVo vo=new BusiContactVo();
        vo.setId(id);
        BusiContactVo po=this.busiContactService.getBusiContact(vo);
        if(po!=null){
            return JsonResponse.success("查找成功！",po);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public Object modify(BusiContactVo data){
        data.setCreateTime(null);
        data.setUpdateTime(new Date());
        int modifyCount=this.busiContactService.modifyBusiContact(data);
        if(modifyCount==1){
            return JsonResponse.success("修改成功！",data);
        }else{
            return JsonResponse.fail("修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    public Object remove(BusiContactVo data){
        int removeCount=this.busiContactService.removeBusiContact(data);
        if(removeCount==1){
            return JsonResponse.success("删除成功！",null);
        }else{
            return JsonResponse.fail("删除失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Object save(@Valid BusiContactVo data){
        data.setCreateTime(new Date());
        data.setUpdateTime(data.getCreateTime());
        if(data.getContactAddrCode()==null || data.getContactAddrCode()==""){
            StringBuilder _contactAddrCode=new StringBuilder("");
            _contactAddrCode.append(data.getProviceCode()).append(":").append(data.getProviceName()).append(";")
                    .append(data.getAreaCode()).append(":").append(data.getAreaName()).append(";")
                    .append(data.getCityCode()).append(":").append(data.getCityName());
            data.setContactAddrCode(_contactAddrCode.toString());
        }
        if(this.busiContactService.save(data)){
            return JsonResponse.success("添加成功！",data);
        }else{
            return JsonResponse.fail("添加失败！");
        }
    }



}
