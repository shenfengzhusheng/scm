package org.xfs.scm.goods.item.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.base.service.impl.BaseServiceImpl;
import org.xfs.scm.goods.item.dao.ItemMapper;
import org.xfs.scm.goods.item.service.ItemServiceI;
import org.xfs.scm.item.entity.Item;
import org.xfs.scm.item.vo.ItemVo;

import com.github.pagehelper.PageHelper;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemServiceI {
	//private final Logger logger=Logger.getLogger(ItemServiceImpl.class);
	
	@Resource
	private ItemMapper itemMapper;
	
	@Override
	public Item getItem(ItemVo vo) {
		List<Item>list=this.listItem(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public List<Item> listItem(ItemVo vo) {
		return this.itemMapper.getItem(vo);
	}
	
	@Transactional(readOnly=false)
	@Override
	public Boolean addItem(ItemVo vo) {
		return this.itemMapper.addItem(vo)==1;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<Item> pageListItem(ItemVo vo, int page, int rows) {
		PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(page, rows);
	//	pageHelper.offsetPage(page, rows);
		return this.itemMapper.getItem(vo);
	}

}
