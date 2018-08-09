package org.xfs.scm.service.base;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml", "classpath:spring/spring-db-config.xml",  "classpath:spring/spring-mybatis.xml","classpath:spring/spring-trans.xml" })
public class BaseServiceTest {
	protected String userName;
	@Before
	public void init(){
	//	System.out.println("--------------------------------init");
		this.userName="系统";
	}
	
	//@Test
	public void doTest(){
		//fail("Not yet implemented");
	}
	@Test
	public void testYes() {
		System.out.println("yes");
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
