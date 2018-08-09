package org.xfs.scm.data.system.role.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.role.po.RolePo;
import org.xfs.scm.data.system.role.po.TreeRole;
import org.xfs.scm.data.system.role.service.RoleServiceI;
import org.xfs.scm.data.system.role.vo.RoleVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;
import org.xfs.scm.platform.util.json.FastJsonUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/role/")
public class RoleWeb extends BaseWeb {


    @Resource
    private RoleServiceI roleService;

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(RoleVo data, Integer page, Integer rows){
        if(page==null || page<=0){
            page=1;
        }
        if(rows==null || rows<=0){
            page=10;
        }
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        return JsonResponse.success("查询成功！",this.roleService.gridRole(data,page,rows));
    }

    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(RoleVo data){
        data.setCreatedTime(new Date());
        data.setCreatedBy(TokenManager.getUserName());
        data.setLastUpdateTime(data.getCreatedTime());
        data.setLastUpdateBy(data.getCreatedBy());
        if(this.roleService.addRole(data)){
            return JsonResponse.success("角色添加成功！",data);
        }else{
            return JsonResponse.fail("角色添加失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(RoleVo data){
        data.setCreatedTime(null);
        data.setCreatedBy(null);
        data.setLastUpdateTime(new Date());
        data.setLastUpdateBy(TokenManager.getUserName());
        if(this.roleService.modfiyRole(data)){
            return JsonResponse.success("角色修改成功！",data);
        }else{
            return JsonResponse.fail("角色修改失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(@RequestParam Integer id){
        if(this.roleService.removeRoles(id)){
            return JsonResponse.success("角色删除成功！",id);
        }else{
            return JsonResponse.fail("角色删除失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Integer id){
        RoleVo data=new RoleVo();
        data.setRid(id);
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        RolePo result=this.roleService.getRole(data);
        if(result!=null){
            return JsonResponse.success("查询成功！",result);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "treeRole.do",method = RequestMethod.GET)
    public Object treeRole(){
        RoleVo data=new RoleVo();
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setUserId(TokenManager.getCurrentUser().getUserId());

        List<TreeRole> result=this.roleService.treeRole(data);
        if(result!=null){
            return JsonResponse.success("查询成功！",result);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

}
