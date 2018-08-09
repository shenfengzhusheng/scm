package org.xfs.scm.data.business.basic.dict.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.basic.dict.service.DictServiceI;
import org.xfs.scm.data.business.basic.dict.vo.DictInfoVo;
import org.xfs.scm.data.business.basic.dict.po.DictPo;
import org.xfs.scm.data.business.basic.dict.vo.DictVo;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/basic/dict/")
public class DictWeb extends BaseWeb{

    @Resource
    private DictServiceI dictServiceI;


    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid(DictVo vo,Integer page,Integer rows){
        if(page==null){
            page=1;
        }
        if(rows==null){
            rows=10;
        }
        Grid<DictPo> grid=this.dictServiceI.grid(vo,page,rows);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "getDictType",method = RequestMethod.POST)
    public Object getDictType(DictVo vo){
        List<DictInfoVo> grid=this.dictServiceI.getDictType(vo);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "getDictTypeDetails",method = RequestMethod.POST)
    public Object getDictTypeDetails(DictVo vo){
        List<DictPo> grid=this.dictServiceI.getDictPos(vo);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable Long id){
        DictPo po=this.dictServiceI.getDictPo(new DictVo(id));
        if(po!=null){
            return JsonResponse.success("查找成功！",po);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public Object modify(DictVo vo){
        int modifyCount=this.dictServiceI.modifyDictPo(vo);
        if(modifyCount==1){
            return JsonResponse.success("修改成功！",vo);
        }else{
            return JsonResponse.fail("修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    public Object remove(Long id){
        int removeCount=this.dictServiceI.removeDictPo(new DictVo(id));
        if(removeCount==1){
            return JsonResponse.success("删除成功！",null);
        }else{
            return JsonResponse.fail("删除失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object add(@Valid DictVo vo){
        vo.setCreatedBy("刘治");
        vo.setLastUpdateBy("刘治");
        int addCount=this.dictServiceI.addDictPo(vo);
        if(addCount==1){
            return JsonResponse.success("添加成功！",vo);
        }else{
            return JsonResponse.fail("添加失败！");
        }
    }



}
