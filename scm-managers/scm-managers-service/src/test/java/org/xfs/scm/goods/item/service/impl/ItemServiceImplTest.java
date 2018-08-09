package org.xfs.scm.goods.item.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.xfs.scm.goods.item.service.ItemServiceI;
import org.xfs.scm.item.entity.Item;
import org.xfs.scm.service.base.BaseServiceTest;

import com.github.pagehelper.PageInfo;

public class ItemServiceImplTest extends BaseServiceTest{
	private static final Logger logger=org.slf4j.LoggerFactory.getLogger(ItemServiceImplTest.class);
	
	@Autowired
	private ItemServiceI itemService;
	
	//@Test
	public void testGetItem() {
		fail("Not yet implemented");
	}

	//@Test
	public void testListItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testYes() {
		System.out.println("yes");
	}
	//@Test
	public void testAddItem() {
		fail("Not yet implemented");
	}

	//@Test
	public void testPageListItem() {
		 try {
				PageInfo<Item>page=this.itemService.queryListByPageAndOrder(null, 1, 20, "price");
				if(page!=null){
					List<Item>list=page.getList();
					logger.info("total:"+page.getTotal());
					for(Item item :list){
						logger.info(item.toString());
					}
				}
			} catch (Exception e) {
				logger.error("query error", e);
			}	}

}
