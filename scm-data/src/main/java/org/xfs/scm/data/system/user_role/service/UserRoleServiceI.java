package org.xfs.scm.data.system.user_role.service;

import java.util.List;

import org.xfs.scm.data.system.user_role.entity.UserRole;
import org.xfs.scm.platform.base.service.BaseServiceI;

public interface UserRoleServiceI extends BaseServiceI<UserRole> {
	List<UserRole> getUserRole(UserRole vo);
	
	boolean removeUserRole(UserRole vo);
	
	boolean removeUserRoleByRid(Integer rid);
	
	boolean removerUserRoleByUserId(Long userId);
	
	boolean grantRole(Long id, List<Integer> ids);
}
