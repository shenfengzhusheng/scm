package org.xfs.scm.data.business.crm.company.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.crm.company.entity.Company;
import org.xfs.scm.data.business.crm.company.service.CompanyServiceI;
import org.xfs.scm.data.business.crm.company.vo.CompanyVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/rest/crm/company/")
public class CompanyWeb extends BaseWeb {

    @Resource
    private CompanyServiceI companyService;



    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(CompanyVo data){
        data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
        data.setCreatedTime(new Date());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setLastUpdateTime(data.getCreatedTime());
        if(this.companyService.addCompany(data)==1){
            return JsonResponse.success("客户添加成功！");
        }else{
            return JsonResponse.fail("客户添加失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(Long id){
        CompanyVo data =new CompanyVo(id);
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.companyService.removeCompany(data)==1){
            return JsonResponse.success("客户删除成功！");
        }else{
            return JsonResponse.fail("客户删除失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(CompanyVo data){
        data.setCreatedBy(null);
        data.setCreatedTime(null);
        data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
        data.setLastUpdateTime(new Date());
        if(this.companyService.modifyCompany(data)==1){
            return JsonResponse.success("客户修改成功！");
        }else{
            return JsonResponse.fail("客户修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable Long id){
        Company customer=this.companyService.getCompany(new CompanyVo(id));
        if(customer!=null){
            return JsonResponse.success("查询成功！",customer);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
    public Object grid(CompanyVo data){
        Grid<Company> grid=this.companyService.grid(data);
        if(grid!=null){
            return JsonResponse.success("查询成功！",grid);
        }else{
            return JsonResponse.fail("查询失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "easyuiGrid.do",method = RequestMethod.POST)
    public Object easyuiGrid(CompanyVo data){
        Grid<Company> grid=this.companyService.grid(data);
        if(grid!=null){
            return grid;
        }else{
           Grid<Company>grid1=new Grid<>();
           return grid1;
        }
    }
}
