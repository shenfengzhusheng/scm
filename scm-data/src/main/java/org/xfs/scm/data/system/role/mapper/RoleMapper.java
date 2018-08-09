package org.xfs.scm.data.system.role.mapper;

import java.util.List;


import com.github.abel533.mapper.Mapper;
import org.xfs.scm.data.system.role.entity.Role;
import org.xfs.scm.data.system.role.po.RolePo;
import org.xfs.scm.data.system.role.po.TreeRole;
import org.xfs.scm.data.system.role.vo.RoleVo;

public interface RoleMapper extends Mapper<Role> {
    List<RolePo> getRole(RoleVo vo);
    List<TreeRole> getRoleTree(RoleVo vo);
    
    
    Long countRole(RoleVo vo);

}