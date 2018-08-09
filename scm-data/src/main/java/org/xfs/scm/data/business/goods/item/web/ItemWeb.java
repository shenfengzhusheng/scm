package org.xfs.scm.data.business.goods.item.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.goods.item.po.ItemPo;
import org.xfs.scm.data.business.goods.item.service.ItemServiceI;
import org.xfs.scm.data.business.goods.item.vo.ItemVo;
import org.xfs.scm.platform.base.web.BaseWeb;

import javax.validation.Valid;

/**
 * Created by 神风逐胜 on 2018/1/8 0008.21:52
 * version:1.0
 */
@Controller
@RequestMapping("/goods/")
public class ItemWeb extends BaseWeb {

    @Autowired
    private ItemServiceI itemServiceI;


    @ResponseBody
    @RequestMapping(value = "grid",method = RequestMethod.POST)
    public Object grid( ItemVo vo,Integer page,Integer rows){
        if(page==null){
            page=1;
        }
        if(rows==null){
            rows=10;
        }
        Grid<ItemPo> grid=this.itemServiceI.grid(vo,page,rows);
        if(grid!=null){
            return JsonResponse.success("查找成功！",grid);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable Long id){
        ItemPo po=this.itemServiceI.getItemPo(new ItemVo(id));
        if(po!=null){
            return JsonResponse.success("查找成功！",po);
        }else{
            return JsonResponse.fail("未查到相关信息！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public Object modify(ItemVo vo){
        int modifyCount=this.itemServiceI.modifyItemPo(vo);
        if(modifyCount==1){
            return JsonResponse.success("修改成功！",vo);
        }else{
            return JsonResponse.fail("修改失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    public Object remove(Long id){
        int removeCount=this.itemServiceI.removeItemPo(new ItemVo(id));
        if(removeCount==1){
            return JsonResponse.success("删除成功！",null);
        }else{
            return JsonResponse.fail("删除失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object add(@Valid ItemVo vo){
        vo.setCreateBy("刘治");
        vo.setLastUpdateBy("刘治");
        int addCount=this.itemServiceI.addItemPo(vo);
        if(addCount==1){
            return JsonResponse.success("添加成功！",vo);
        }else{
            return JsonResponse.fail("添加失败！");
        }
    }
}
