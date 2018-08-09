package org.xfs.scm.system.resources.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.base.service.impl.BaseServiceImpl;
import org.xfs.scm.system.resources.dao.ResourcesMapper;
import org.xfs.scm.system.resources.entity.Resources;
import org.xfs.scm.system.resources.po.ResourcePo;
import org.xfs.scm.system.resources.service.ResourcesServiceI;
import org.xfs.scm.system.resources.vo.ResourcesVo;

@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesServiceI {
	
	
	
	@Resource ResourcesMapper resourcesMapper;
	
	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<ResourcePo> getResources(ResourcesVo vo) {
		List<ResourcePo> list=this.resourcesMapper.getResources(vo);
		if(!list.isEmpty()){
			List<ResourcePo>l=new ArrayList<ResourcePo>();
			l.addAll(list);
			Collections.sort(l, new Comparator<ResourcePo>(){// 排序
				@Override
				public int compare(ResourcePo o1, ResourcePo o2) {
					if(o1.getSeq()==null){
						o1.setSeq(1000);
					}
					if(o2.getSeq()==null){
						o2.setSeq(1000);
					}
					return o1.getSeq().compareTo(o2.getSeq());
				}
				
			});
		    return l;
		}
		return list;
	}

	@Override
	public ResourcePo getById(String id) {
		List<ResourcePo>list=this.getResources(new ResourcesVo(id));
		if(list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<ResourcePo> getUserResources(Long userId) {
		ResourcesVo vo=new ResourcesVo();
		vo.setUserId(userId);
		return this.getResources(vo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public boolean removeResources(String rsid) throws Exception {
		return this.resourcesMapper.removeResources(rsid)==1;
	}

	@Override
	public List<ResourcePo> getRoleResources(Integer rid) {
		ResourcesVo vo=new ResourcesVo();
		vo.setRid(rid);
		return this.getResources(vo);
	}

	@Override
	public boolean removeResources(ResourcesVo vo) {
		return false;
	}
	

}
