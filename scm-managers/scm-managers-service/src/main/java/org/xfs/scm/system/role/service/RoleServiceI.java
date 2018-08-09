package org.xfs.scm.system.role.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.system.role.entity.Role;
import org.xfs.scm.system.role.po.RolePo;
import org.xfs.scm.system.role.po.TreeRole;
import org.xfs.scm.system.role.vo.RoleVo;

public interface RoleServiceI extends BaseServiceI<Role> {
	Boolean removeRoles(Integer rid);
	
	
	List<RolePo>listRole(RoleVo vo);
	
	RolePo getRole(RoleVo vo);
	
	RolePo getById(Integer roleId);
	
	boolean addRole(RoleVo vo);
	
	boolean modfiyRole(RoleVo vo);
	
	Grid gridRole(RoleVo vo, int page, int rows);
	
	
	List<TreeRole> treeRole(RoleVo vo);
}
