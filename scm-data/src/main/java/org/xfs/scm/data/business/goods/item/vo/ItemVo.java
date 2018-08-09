package org.xfs.scm.data.business.goods.item.vo;

import org.xfs.scm.data.business.goods.item.po.ItemPo;

@SuppressWarnings("serial")
public class ItemVo extends ItemPo {

    public ItemVo(){}
    public ItemVo(Long itemId){
        super.setItemId(itemId);
    }
    public ItemVo(String itemCode){
        super.setItemCode(itemCode);
    }
}
