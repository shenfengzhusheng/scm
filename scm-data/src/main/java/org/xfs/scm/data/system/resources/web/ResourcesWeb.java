package org.xfs.scm.data.system.resources.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.data.system.resources.po.ResourcePo;
import org.xfs.scm.data.system.resources.service.ResourcesServiceI;
import org.xfs.scm.data.system.resources.vo.ResourcesVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/sys/resources/")
public class ResourcesWeb extends BaseWeb {


    @Resource
    private ResourcesServiceI resourcesService;

//    @ResponseBody
//    @RequestMapping(value = "grid.do",method = RequestMethod.POST)
//    public Object grid(ResourcesVo data, Integer page, Integer rows){
//        if(page==null || page<=0){
//            page=1;
//        }
//        if(rows==null || rows<=0){
//            page=10;
//        }
//        return JsonResponse.success("查询成功！",this.resourcesService.gridRole(data,page,rows));
//    }

    @ResponseBody
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public Object save(ResourcesVo data){
        data.setCreatedTime(new Date());
        data.setCreatedBy(TokenManager.getUserName());
        data.setLastUpdateTime(data.getCreatedTime());
        data.setLastUpdateBy(data.getCreatedBy());
        data.setRsid(IdGenerator.generator());
        if(data.getRsType()!=null){
            if(data.getRsType().equals("Menu")){
                data.setIconcls("fa fa-book");
            }else if(data.getRsType().equals("Function")){
                data.setIconcls("fa fa-leaf");
            }else if(data.getRsType().equals("Module")){
                data.setIconcls("fa fa-folder-open");
            }

        }
        if(this.resourcesService.saveSelective(data)==1){
            return JsonResponse.success("资源添加成功！",data);
        }else{
            return JsonResponse.fail("资源添加失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "modify.do",method = RequestMethod.POST)
    public Object modify(ResourcesVo data){
        data.setCreatedTime(null);
        data.setCreatedBy(null);
        data.setLastUpdateTime(new Date());
        data.setLastUpdateBy(TokenManager.getUserName());
        if(this.resourcesService.updateByIdSelective(data)==1){
            return JsonResponse.success("资源修改成功！",data);
        }else{
            return JsonResponse.fail("资源修改失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "remove.do",method = RequestMethod.POST)
    public Object remove(@RequestParam String id){
        if(this.resourcesService.deleteById(id)==1){
            return JsonResponse.success("资源删除成功！",id);
        }else{
            return JsonResponse.fail("资源删除失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
    public Object info(@PathVariable String id){

        ResourcePo result=this.resourcesService.getById(id);
        if(result!=null){
            return JsonResponse.success("资源获取成功！",result);
        }else{
            return JsonResponse.fail("资源获取失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "treeGrid.do",method = RequestMethod.POST)
    public Object treeGrid(ResourcesVo data){
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        return this.resourcesService.getResources(data);
    }

    @ResponseBody
    @RequestMapping(value = "tree.do",method = RequestMethod.POST)
    public Object tree(ResourcesVo data){
        data.setUserId(TokenManager.getCurrentUser().getUserId());
        data.setSuperFlag(TokenManager.getCurrentUser().getSuperFlag());
        return JsonResponse.success("资源获取成功！",this.resourcesService.tree(data));
       // return this.resourcesService
    }

}
