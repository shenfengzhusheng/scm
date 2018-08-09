package org.xfs.scm.data.system.role_resources.service;

import org.xfs.scm.common.exception.BusinessException;
import org.xfs.scm.data.system.role_resources.entity.RoleResources;
import org.xfs.scm.platform.base.service.BaseServiceI;

import java.util.List;


public interface RoleResourcesServiceI extends BaseServiceI<RoleResources> {
	
	boolean grant(Integer id, List<String> ids)throws BusinessException;

	List<String> getRoleResources(Integer rid);
	

}
