package org.xfs.scm.data.system.resources.mapper;

import java.util.List;


import com.github.abel533.mapper.Mapper;
import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.resources.po.ResourcePo;
import org.xfs.scm.data.system.resources.vo.ResourcesVo;

public interface ResourcesMapper extends Mapper<Resources> {

    List<ResourcePo> getResources(ResourcesVo vo);
    
    int removeResources(String rsid);
}