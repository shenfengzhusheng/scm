package org.xfs.scm.system.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.base.service.impl.BaseServiceImpl;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.system.role.dao.RoleMapper;
import org.xfs.scm.system.role.entity.Role;
import org.xfs.scm.system.role.po.RolePo;
import org.xfs.scm.system.role.po.TreeRole;
import org.xfs.scm.system.role.service.RoleServiceI;
import org.xfs.scm.system.role.vo.RoleVo;
import org.xfs.scm.system.role_resources.dao.RoleResourcesMapper;
import org.xfs.scm.util.BeanUtils;

import com.github.pagehelper.PageHelper;

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
			if(this.roleResourcesMappper.removeRoleResources(rid)>0)
				return true;
			return false;
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
			BeanUtils.copyNotNullProperties(vo, role);
			if(this.updateByIdSelective(role)==1){
				return true;
			}
			return false;
		}
		return false;
	}

	@SuppressWarnings("static-access")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public Grid gridRole(RoleVo vo, int page, int rows) {
		Grid grid=new Grid();
		PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(page, rows,false);
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
