package org.xfs.scm.business.bd.area.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xfs.scm.business.bd.area.entity.Area;
import org.xfs.scm.business.bd.area.service.AreaServiceI;
import org.xfs.scm.business.bd.area.vo.AreaVo;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.service.base.BaseServiceTest;

public class AreaServiceImplTest extends BaseServiceTest {
	
	@Autowired
	private AreaServiceI service;
	
	//@Test
	public void testRemoveArea() {
		fail("Not yet implemented");
	}

	//@Test
	public void testAddArea() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetAreas() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetArea() {
		fail("Not yet implemented");
	}

	@Test
	public void testGrid() {
		AreaVo vo=new AreaVo();
		vo.setAreaLevel(1);
		Grid<Area>grid=this.service.grid(vo, 1, 20);
		System.out.println("grid"+grid.getTotal());
		if(grid!=null) {
			List<Area>list=grid.getRows();
			for(Area a:list) {
				System.out.println(a);

			}
		}
		//fail("Not yet implemented");
	}

	//@Test
	public void testModifyArea() {
		fail("Not yet implemented");
	}

}
