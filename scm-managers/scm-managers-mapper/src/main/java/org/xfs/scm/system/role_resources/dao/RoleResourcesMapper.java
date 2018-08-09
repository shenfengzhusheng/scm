package org.xfs.scm.system.role_resources.dao;

import java.util.List;

import org.xfs.scm.system.role_resources.entity.RoleResources;
import org.xfs.scm.system.role_resources.vo.RoleResourcesVo;

import com.github.abel533.mapper.Mapper;

public interface RoleResourcesMapper extends Mapper<RoleResources> {

    List<RoleResources> getRoleResources(RoleResourcesVo vo);
    
    int addRoleResources(List<RoleResourcesVo> list);
    
    int removeRoleResources(Integer rid);
}