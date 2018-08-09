package org.xfs.scm.goods.item.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.item.entity.Item;
import org.xfs.scm.item.vo.ItemVo;

public interface ItemServiceI extends BaseServiceI<Item> {
	Item getItem(ItemVo vo);
	List<Item> listItem(ItemVo vo);
	List<Item> pageListItem(ItemVo vo, int page, int rows);

	Boolean addItem(ItemVo vo);
}
