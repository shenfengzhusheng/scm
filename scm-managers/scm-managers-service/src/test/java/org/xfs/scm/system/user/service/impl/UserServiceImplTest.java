package org.xfs.scm.system.user.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.service.base.BaseServiceTest;
import org.xfs.scm.system.user.service.UserServiceI;
import org.xfs.scm.system.user.vo.UserVo;

public class UserServiceImplTest extends BaseServiceTest {
	private static final Logger logger=LoggerFactory.getLogger(UserServiceImplTest.class);
	
	
	@Resource
	private UserServiceI userService;
	@Test
	public void testLogin() {
		UserVo vo=new UserVo();
		vo.setUserCode("fengling9874");
		vo.setPwd("376584");
		
		logger.info("------------------------------"+this.userService.login(vo));
		//fail("Not yet implemented");
	}

}
