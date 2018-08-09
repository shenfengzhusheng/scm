package org.xfs.scm.system.user_role.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.system.user_role.entity.UserRole;

public interface UserRoleServiceI extends BaseServiceI<UserRole> {
	List<UserRole> getUserRole(UserRole vo);
	
	boolean removeUserRole(UserRole vo);
	
	boolean removeUserRoleByRid(Integer rid);
	
	boolean removerUserRoleByUserId(Long userId);
	
	boolean grantRole(Long id, String ids);
}
