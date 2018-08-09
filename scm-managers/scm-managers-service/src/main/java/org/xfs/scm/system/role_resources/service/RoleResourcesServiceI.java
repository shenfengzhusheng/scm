package org.xfs.scm.system.role_resources.service;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.common.exception.BusinessException;
import org.xfs.scm.system.role_resources.entity.RoleResources;

public interface RoleResourcesServiceI extends BaseServiceI<RoleResources> {
	
	boolean grant(Integer id, String ids)throws BusinessException;
	

}
