package org.xfs.scm.system.organization.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.system.organization.entity.Organization;
import org.xfs.scm.system.organization.po.OrganizationPo;
import org.xfs.scm.system.organization.vo.OrganizationVo;

public interface OrganizationServiceI extends BaseServiceI<Organization> {
	Boolean removeOrganization(OrganizationVo vo);
	
	List<OrganizationPo>listOrganization(OrganizationVo vo)throws Exception;
	
	OrganizationPo getOrganization(OrganizationVo vo)throws Exception;
	
	OrganizationPo getById(Long oid)throws Exception;
	
	boolean addOrganization(OrganizationVo vo)throws Exception;
	
	Grid grid(OrganizationVo vo, int page, int rows)throws Exception;
}
