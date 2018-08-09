package org.xfs.scm.data.system.organization.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.system.organization.entity.Organization;
import org.xfs.scm.data.system.organization.po.OrganizationPo;
import org.xfs.scm.data.system.organization.po.OrganizationTree;
import org.xfs.scm.data.system.organization.vo.OTree;
import org.xfs.scm.data.system.organization.vo.OrganizationVo;
import org.xfs.scm.platform.base.service.BaseServiceI;

import java.util.List;


public interface OrganizationServiceI extends BaseServiceI<Organization> {
	Boolean removeOrganization(OrganizationVo vo);
	
	List<OrganizationPo>listOrganization(OrganizationVo vo);
	
	OrganizationPo getOrganization(OrganizationVo vo);
	
	OrganizationPo getById(Long oid);
	
	boolean addOrganization(OrganizationVo vo);
	
	Grid<OrganizationPo> grid(OrganizationVo vo, int page, int rows);

	List<OTree<OrganizationPo>> getOrganizationTree(OrganizationVo vo);


	List<OrganizationTree>tree(OrganizationVo vo);
}
