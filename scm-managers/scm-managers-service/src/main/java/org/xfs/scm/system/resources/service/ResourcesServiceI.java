package org.xfs.scm.system.resources.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.system.resources.entity.Resources;
import org.xfs.scm.system.resources.po.ResourcePo;
import org.xfs.scm.system.resources.vo.ResourcesVo;


public interface ResourcesServiceI extends BaseServiceI<Resources> {
	 
	 List<ResourcePo> getUserResources(Long userId);
	 
	 List<ResourcePo> getRoleResources(Integer rid);

	 List<ResourcePo> getResources(ResourcesVo vo);
	 
	 ResourcePo getById(String id);
	 
	 boolean removeResources(String rsid)throws Exception;
	 
	 boolean removeResources(ResourcesVo vo);
	 


}
