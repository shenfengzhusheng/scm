package org.xfs.scm.system.dept.service.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.xfs.scm.service.base.BaseServiceTest;
import org.xfs.scm.system.dept.po.DeptPo;
import org.xfs.scm.system.dept.service.DeptServiceI;
import org.xfs.scm.system.dept.vo.DeptVo;

public class DeptServiceImplTest extends BaseServiceTest {

	
	@Resource DeptServiceI deptService;
	
	//@Test
	public void testRemoveDept() {
		fail("Not yet implemented");
	}

	//@Test
	public void testList() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetDept() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetByIdLong() {
		DeptPo po=this.deptService.getById(1l);
		System.out.println(po);
	}

	//@Test
	public void testAddDept() {
		DeptVo vo=new DeptVo();
		vo.setDeptCode("test0001");
		vo.setDeptName("运营中心");
		vo.setOid(1L);
		vo.setCreatedBy(super.getUserName());
		vo.setLastUpdateBy(vo.getCreatedBy());
		vo.setChanger("无");
		vo.setCtype(Byte.valueOf("1"));
		vo.setState(Byte.valueOf("1"));
		System.out.println(this.deptService.addDept(vo));
	}

	@Test
	public void testYes() {
		System.out.println("yes");
	}
	//@Test
	public void testGrid() {
		fail("Not yet implemented");
	}

	//@Test
	public void testModifyDept() {
		fail("Not yet implemented");
	}

}
