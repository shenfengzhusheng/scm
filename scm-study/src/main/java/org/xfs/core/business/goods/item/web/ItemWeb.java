package org.xfs.core.business.goods.item.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.core.business.goods.item.po.ItemPo;
import org.xfs.core.business.goods.item.service.ItemServiceI;
import org.xfs.core.platform.base.model.Json;
import org.xfs.core.platform.base.web.BaseWeb;

@RestController
@RequestMapping("/item")
public class ItemWeb extends BaseWeb {

    @Resource
    ItemServiceI itemService;

    @RequestMapping(value = "/add")
    public Json<ItemPo> add(ItemPo vo) {
        Json<ItemPo> json = new Json<ItemPo>();
        json.setCode("0");
        json.setData(vo);
        json.setMessage("添加失败");
        if (itemService.addItem(vo) == 1) {
            json.setCode("1");
            json.setMessage("添加成功！");

        }
        return json;
    }

    @RequestMapping(value = "/modfiy")
    public Json<ItemPo> modfiy(ItemPo vo) {
        Json<ItemPo> json = new Json<ItemPo>();
        json.setCode("0");
        json.setData(vo);
        json.setMessage("修改失败");
        if (itemService.modifyItem(vo) == 1) {
            json.setCode("1");
            json.setMessage("修改成功！");

        }
        return json;
    }


    @RequestMapping(value = "/remove")
    public Json<ItemPo> remove(ItemPo vo) {
        Json<ItemPo> json = new Json<ItemPo>();
        json.setCode("0");
        json.setData(vo);
        json.setMessage("删除失败");
        int count = this.itemService.removeItem(vo);
        if (count == 1) {
            json.setCode("1");
            json.setMessage("删除成功！");

        }
        return json;
    }
}
