package org.xfs.scm.data.system.user.web;

import jdk.nashorn.internal.parser.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.service.UserServiceI;
import org.xfs.scm.data.system.user.vo.UserVo;
import org.xfs.scm.data.system.user_role.entity.UserRole;
import org.xfs.scm.data.system.user_role.service.UserRoleServiceI;
import org.xfs.scm.data.system.user_role.vo.UserRoleVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/user/")
public class UserWeb extends BaseWeb {

    @Resource
    private UserServiceI userService;

    @Resource
    private UserRoleServiceI userRoleService;

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(UserVo data,Integer page,Integer rows){
        if(page==null || page<=0){
            page=1;
        }
        if(rows==null || rows<=0){
            page=10;
        }
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setCustId(TokenManager.getCurrentUser().getCustId());
        return JsonResponse.success("查询成功！",this.userService.grid(data,page,rows));
    }

    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(UserVo data){
        data.setCreatedTime(new Date());
        data.setCreatedBy(TokenManager.getUserName());
        data.setCustId(TokenManager.getCurrentUser().getCustId());
        data.setLastUpdateTime(data.getCreatedTime());
        data.setLastUpdateBy(data.getCreatedBy());
        if(this.userService.addUser(data)){
            return JsonResponse.success("用户添加成功！",data);
        }else{
            return JsonResponse.fail("用户添加失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(UserVo data){
        data.setCreatedTime(null);
        data.setCreatedBy(null);
        data.setLastUpdateTime(new Date());
        data.setLastUpdateBy(TokenManager.getUserName());
        if(this.userService.modifyUser(data)){
            return JsonResponse.success("用户修改成功！",data);
        }else{
            return JsonResponse.fail("用户修改失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(@RequestParam Long id){
        UserVo data=new UserVo();
        data.setUserId(id);
        if(this.userService.removeUser(data)){
            return JsonResponse.success("用户删除成功！",id);
        }else{
            return JsonResponse.fail("用户删除失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        UserVo data=new UserVo();
        data.setUserId(id);
        UserPo result=this.userService.getUser(data);
        if(result!=null){
            return JsonResponse.success("用户获取成功！",result);
        }else{
            return JsonResponse.fail("用户获取失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "userRole.do/{id}",method = RequestMethod.GET)
    public Object userRole(@PathVariable Long id){
        UserRole data=new UserRole();
        data.setUserId(id);
        List<UserRole> result=this.userRoleService.getUserRole(data);
        if(result!=null){
            List<Integer>list=new ArrayList<Integer>();
            for(UserRole ur:result){
                list.add(ur.getRid());
            }
            return JsonResponse.success("获取用户角色成功！",list);
        }else{
            return JsonResponse.fail("获取用户角色失败！");
        }
    }



    @ResponseBody
    @RequestMapping(value = "grantUserRole.do",method = RequestMethod.POST)
    public Object grantUserRole(@Valid UserRoleVo data){

        if(this.userRoleService.grantRole(data.getUserId(),data.getRids())){

            return JsonResponse.success("用户角色授权成功！");
        }else{
            return JsonResponse.fail("用户角色授权失败！");
        }
    }
}
