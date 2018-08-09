package org.xfs.scm.system.resources.dao;

import java.util.List;

import org.xfs.scm.system.resources.entity.Resources;
import org.xfs.scm.system.resources.po.ResourcePo;
import org.xfs.scm.system.resources.vo.ResourcesVo;

import com.github.abel533.mapper.Mapper;

public interface ResourcesMapper extends Mapper<Resources> {

    List<ResourcePo> getResources(ResourcesVo vo);
    
    int removeResources(String rsid);
}