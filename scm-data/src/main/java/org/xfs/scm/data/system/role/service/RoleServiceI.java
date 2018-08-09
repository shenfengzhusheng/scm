package org.xfs.scm.data.system.role.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.system.role.entity.Role;
import org.xfs.scm.data.system.role.po.RolePo;
import org.xfs.scm.data.system.role.po.TreeRole;
import org.xfs.scm.data.system.role.vo.RoleVo;
import org.xfs.scm.platform.base.service.BaseServiceI;

import java.util.List;



public interface RoleServiceI extends BaseServiceI<Role> {
	Boolean removeRoles(Integer rid);
	
	
	List<RolePo>listRole(RoleVo vo);
	
	RolePo getRole(RoleVo vo);
	
	RolePo getById(Integer roleId);
	
	boolean addRole(RoleVo vo);
	
	boolean modfiyRole(RoleVo vo);
	
	Grid<RolePo> gridRole(RoleVo vo, int page, int rows);
	
	
	List<TreeRole> treeRole(RoleVo vo);
}
