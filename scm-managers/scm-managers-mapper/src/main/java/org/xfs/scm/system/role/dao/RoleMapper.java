package org.xfs.scm.system.role.dao;

import java.util.List;

import org.xfs.scm.system.role.entity.Role;
import org.xfs.scm.system.role.po.RolePo;
import org.xfs.scm.system.role.po.TreeRole;
import org.xfs.scm.system.role.vo.RoleVo;

import com.github.abel533.mapper.Mapper;

public interface RoleMapper extends Mapper<Role> {
    List<RolePo> getRole(RoleVo vo);
    List<TreeRole> getRoleTree(RoleVo vo);
    
    
    Long countRole(RoleVo vo);

}