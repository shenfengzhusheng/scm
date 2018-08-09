package org.xfs.scm.data.system.role_resources.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.system.role_resources.service.RoleResourcesServiceI;
import org.xfs.scm.data.system.role_resources.vo.GrantVo;

import javax.annotation.Resource;
import javax.validation.Valid;


@Controller
@RequestMapping("/sys/roleResources/")
public class RoleResourcesWeb {

    @Resource
    private RoleResourcesServiceI roleResourcesService;

    @ResponseBody
    @RequestMapping("/getRolehasAuthority.do")
    public Object queryRoleResources(Integer id){
        if(id==null ||id==0){
            return JsonResponse.fail("角色Id未传！");
        }
        return JsonResponse.success("查询成功！",this.roleResourcesService.getRoleResources(id));
    }
    @ResponseBody
    @RequestMapping("/grantAuthority.do")
    public Object grantAuthority(@Valid GrantVo vo){
        if(this.roleResourcesService.grant(vo.getRid(),vo.getIds())) {
            return JsonResponse.success("授权成功！");
        }
        return JsonResponse.fail("授权");

    }


}
