package org.xfs.scm.data.system.role_resources.mapper;

import java.util.List;

import org.xfs.scm.data.system.role_resources.entity.RoleResources;
import org.xfs.scm.data.system.role_resources.vo.RoleResourcesVo;

import com.github.abel533.mapper.Mapper;

public interface RoleResourcesMapper extends Mapper<RoleResources> {

    List<RoleResources> getRoleResources(RoleResourcesVo vo);
    
    int addRoleResources(List<RoleResourcesVo> list);
    
    int removeRoleResources(Integer rid);
}