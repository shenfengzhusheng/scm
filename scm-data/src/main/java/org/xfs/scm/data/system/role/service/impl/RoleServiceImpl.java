package org.xfs.scm.data.system.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.data.system.role.entity.Role;
import org.xfs.scm.data.system.role.mapper.RoleMapper;
import org.xfs.scm.data.system.role.po.RolePo;
import org.xfs.scm.data.system.role.po.TreeRole;
import org.xfs.scm.data.system.role.service.RoleServiceI;
import org.xfs.scm.data.system.role.vo.RoleVo;
import org.xfs.scm.data.system.role_resources.mapper.RoleResourcesMapper;
import org.xfs.scm.platform.base.service.impl.BaseServiceImpl;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleServiceI {
	
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private RoleResourcesMapper roleResourcesMappper;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public Boolean removeRoles(Integer rid) {
		if(this.deleteById(rid)==1){
			//同时删除角色权限
			this.roleResourcesMappper.removeRoleResources(rid);
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<RolePo> listRole(RoleVo vo) {
		return this.roleMapper.getRole(vo);
	}

	@Override
	public RolePo getRole(RoleVo vo) {
		List<RolePo> list=this.listRole(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public RolePo getById(Integer roleId) {
		return this.getRole(new RoleVo(roleId));
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean addRole(RoleVo vo) {
		if(vo!=null){
			Role role=new Role();
			BeanUtils.copyNotNullProperties(vo, role);
			if(this.saveSelective(role)==1){
				return true;
			}
			return false;
		}
		return false;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public boolean modfiyRole(RoleVo vo) {
		if(vo!=null){
			Role role=new Role();
			String[]ignoreProperties={"createdBy","createdTime"};
			BeanUtils.copyNotNullProperties(vo, role,ignoreProperties);
			if(this.updateByIdSelective(role)==1){
				return true;
			}
			return false;
		}
		return false;
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public Grid<RolePo> gridRole(RoleVo vo, int page, int rows) {
		Grid<RolePo> grid=new Grid<RolePo>();
		grid.setRows(this.roleMapper.getRole(vo));
		grid.setTotal(this.roleMapper.countRole(vo));
		return grid;
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<TreeRole> treeRole(RoleVo vo) {
		return this.roleMapper.getRoleTree(vo);
	}
	
}
