package org.xfs.scm.system.organization.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.service.base.BaseServiceTest;
import org.xfs.scm.system.organization.po.OrganizationPo;
import org.xfs.scm.system.organization.service.OrganizationServiceI;
import org.xfs.scm.system.organization.vo.OrganizationVo;

public class OrganizationServiceImplTest extends BaseServiceTest {

	@Resource OrganizationServiceI serivce;
	
	//@Test
	public void testRemoveOrganization() {
		OrganizationVo o=new OrganizationVo();
		o.setCreatedBy(super.userName);
		o.setLastUpdateBy(super.userName);
		o.setOcode("0002");
		o.setOname("广东金意陶陶瓷有限公司");
		o.setState(Byte.valueOf("1"));
		o.setMemo("陶资公司");
		try {
			System.out.println("----------------------->"+this.serivce.addOrganization(o));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented");
	}

	//@Test
	public void testSave() {
		 
		try {
			OrganizationVo vo=new OrganizationVo();
			vo.setOcode("2");
			Grid grid=this.serivce.grid(vo, 1,10);
			if(grid!=null){
				
				@SuppressWarnings("unchecked")
				List<OrganizationPo>list=grid.getRows();
				for(OrganizationPo po:list){
					System.out.println(po);
				}
				
				System.out.println("total:"+grid.getTotal());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented");
	}
	@Test
	public void testYes() {
		System.out.println("yes");
	}
}
