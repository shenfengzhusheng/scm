package org.xfs.scm.data.system.organization.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.session.SessionInfo;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.data.system.organization.entity.Organization;
import org.xfs.scm.data.system.organization.po.OrganizationPo;
import org.xfs.scm.data.system.organization.po.OrganizationTree;
import org.xfs.scm.data.system.organization.service.OrganizationServiceI;
import org.xfs.scm.data.system.organization.vo.OTree;
import org.xfs.scm.data.system.organization.vo.OrganizationVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;
import org.xfs.scm.platform.util.ConfigUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/24 0024.20:05
 * version:1.0
 */
@Controller
@RequestMapping(value = "/rest/sys/organization/")
public class OrganizationWeb extends BaseWeb {
    @Override
    public void initBinder(ServletRequestDataBinder binder) {
        super.initBinder(binder);
    }

    @Autowired
    private OrganizationServiceI organizationService;

    @ResponseBody
    @RequestMapping(value = "tree",method = RequestMethod.POST)
    public Object treeOrganization(OrganizationVo data)throws Exception{
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setCustId(TokenManager.getCurrentUser().getCustId());

        return JsonResponse.success("查询成功！",this.organizationService.getOrganizationTree(data));
    }

    @ResponseBody
    @RequestMapping(value = "treeGrid.do",method = RequestMethod.POST)
    public Object treeGrid(OrganizationVo data)throws Exception{
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        data.setCustId(TokenManager.getCurrentUser().getCustId());
        List<OrganizationTree> tree=this.organizationService.tree(data);
        if(tree.isEmpty()){
            tree=new ArrayList<>();
        }
        return tree;
    }
    @RequestMapping("/grid.do")
    @ResponseBody
    public Object grid(OrganizationVo vo, int page, int rows)throws Exception{
        SessionInfo sessionInfo = (SessionInfo) this.getSession().getAttribute(ConfigUtil.getSessionInfoName());
        if(sessionInfo!=null){
            for(String str:sessionInfo.getResourceList()){
                if(str.contains("organization")){
                    System.out.println(str);
                }
            }
        }
        Grid<OrganizationPo> grid=this.organizationService.grid(vo, page, rows);
        if(grid!=null){
            return JsonResponse.success("查询成功",grid);
        }else{
            return JsonResponse.fail("查找失败！");
        }
    }

    @RequestMapping("/quickSearch.do")
    @ResponseBody
    public Grid<OrganizationPo> quickSearch(OrganizationVo vo, int page, int rows)throws Exception{
        Grid<OrganizationPo> grid=this.organizationService.grid(vo, page, rows);
        return grid;
    }

    @RequestMapping(value = "/info.do/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object info(@PathVariable Long id)throws Exception{
        OrganizationVo vo=new OrganizationVo();
        vo.setOid(id);
        vo.setCustId(TokenManager.getCurrentUser().getCustId());
        OrganizationPo op=this.organizationService.getOrganization(vo);
    //    OrganizationPo op=this.organizationService.getById(id);
       if(op!=null){
           return JsonResponse.success("获取成功！",op);
       }else{
           return JsonResponse.fail("查询失败，系统异常！");
       }

    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(OrganizationVo data){
        data.setLastUpdateBy(TokenManager.getUserName());
        data.setLastUpdateTime(new Date());
        if(data.getOid()!=0){
            Organization entity=new Organization();
            BeanUtils.copyNotNullProperties(data, entity);
            if(this.organizationService.updateByIdSelective(entity)==1){
                return JsonResponse.success("修改成功！",entity);
            }
        }
         return JsonResponse.fail("修改失败！");

    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(@RequestParam Long id){
        int removeCount=this.organizationService.deleteById(id);
        if(removeCount==1){
            return JsonResponse.success("删除成功！",null);
        }else{
            return JsonResponse.fail("删除失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save( Organization data){
        //SessionInfo sessionInfo = (SessionInfo)  this.getSession().getAttribute("sessionInfo");
        data.setCustId(TokenManager.getCurrentUser().getCustId());
        data.setCreatedBy(TokenManager.getUserName());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setCreatedTime(new Date());
        data.setLastUpdateTime(data.getCreatedTime());
        data.setCustId(TokenManager.getCurrentUser().getCustId());
        int addCount=this.organizationService.save(data);
        if(addCount==1){
            return JsonResponse.success("添加成功！",data);
        }else{
            return JsonResponse.fail("添加失败！");
        }
    }

}
