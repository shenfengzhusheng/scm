package org.xfs.scm.data.system.resources.service;

import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.resources.po.ResourcePo;
import org.xfs.scm.data.system.resources.vo.ResourcesVo;
import org.xfs.scm.platform.base.model.AuthorityTree;
import org.xfs.scm.platform.base.service.BaseServiceI;

import java.util.List;



public interface ResourcesServiceI extends BaseServiceI<Resources> {
	 
	 List<ResourcePo> getUserResources(Long userId);
	 
	 List<ResourcePo> getRoleResources(Integer rid);

	 List<ResourcePo> getResources(ResourcesVo vo);
	 
	 ResourcePo getById(String id);
	 
	 boolean removeResources(String rsid)throws Exception;
	 
	 boolean removeResources(ResourcesVo vo);

	 List<AuthorityTree>tree(ResourcesVo vo);


}
