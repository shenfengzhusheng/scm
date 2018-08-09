package org.xfs.scm.system.init.service.impl;


import javax.annotation.Resource;

import org.junit.Test;
import org.xfs.scm.service.base.BaseServiceTest;
import org.xfs.scm.system.init.service.InitServiceI;

public class InitServiceImplTest extends BaseServiceTest {
	
	@Resource
	private InitServiceI initService;
	@Test
	public void testInit() {
		this.initService.init();
	}
//	@Test
	public void testYes() {
		System.out.println("yes");
	}

}
