package org.xfs.scm.data.business.basic.areacode.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.basic.areacode.po.Area;
import org.xfs.scm.data.business.basic.areacode.service.AreaServiceI;
import org.xfs.scm.data.business.basic.areacode.vo.AreaVo;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/basic/area/")
public class AreaWeb {

    @Autowired
    private AreaServiceI serviceI;

    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid(AreaVo vo, Integer page, Integer rows){
        if(page==null){
            page=1;
        }
        if(rows==null){
            rows=10;
        }
        Grid<Area> grid=this.serviceI.grid(vo,page,rows);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryArea",method = RequestMethod.POST)
    public Object queryArea(AreaVo vo){
        List<Area>list=this.serviceI.getAreas(vo);
        if(list!=null){
            return JsonResponse.success("查找成功！",list);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "getParentArea",method = RequestMethod.GET)
    public Object grid(){
        AreaVo vo=new AreaVo();
        vo.setAreaLevelWithOpt("1");
        List<Area> list=this.serviceI.getAreas(vo);
        if(list!=null){
            return JsonResponse.success("查找成功！",list);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id){
        AreaVo vo=new AreaVo();
        vo.setAreaCode(id);
        Area po=this.serviceI.getArea(vo);
        if(po!=null){
            return JsonResponse.success("查找成功！",po);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public Object modify(Area vo){
        int modifyCount=this.serviceI.modifyArea(vo);
        if(modifyCount==1){
            return JsonResponse.success("修改成功！",vo);
        }else{
            return JsonResponse.fail("修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    public Object remove(String id){
        int removeCount=this.serviceI.removeArea(new Area(id));
        if(removeCount==1){
            return JsonResponse.success("删除成功！",null);
        }else{
            return JsonResponse.fail("删除失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object add(@Valid Area vo){

        int addCount=this.serviceI.addArea(vo);
        if(addCount==1){
            return JsonResponse.success("添加成功！",vo);
        }else{
            return JsonResponse.fail("添加失败！");
        }
    }

}
