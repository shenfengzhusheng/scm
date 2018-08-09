package org.xfs.scm.data.system.resources.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.resources.mapper.ResourcesMapper;
import org.xfs.scm.data.system.resources.po.ResourcePo;
import org.xfs.scm.data.system.resources.service.ResourcesServiceI;
import org.xfs.scm.data.system.resources.vo.ResourcesVo;
import org.xfs.scm.platform.base.model.AuthorityTree;
import org.xfs.scm.platform.base.service.impl.BaseServiceImpl;

@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesServiceI {
	
	
	
	@Resource
	ResourcesMapper resourcesMapper;
	
	
	
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

	@Override
	public List<AuthorityTree> tree(ResourcesVo vo) {
		List<ResourcePo>resources=this.getResources(vo);
		//一级菜单
		List<AuthorityTree>trees=new ArrayList<AuthorityTree>();

		if(!resources.isEmpty()){
			List<ResourcePo>last=new ArrayList<ResourcePo>();
			for(ResourcePo resourcePo:resources){
				if(StringUtil.isEmpty(resourcePo.getPid())){
					trees.add(this.buildAuthorityTree(resourcePo));
				}else{
					last.add(resourcePo);
				}
			}
			//递归子级菜单功能
			for (AuthorityTree authorityTree:trees ){
				authorityTree.setChildren(this.recursionChildern(last,authorityTree.getId()));
			}
		}
		return trees;
	}

	public List<AuthorityTree> recursionChildern(List<ResourcePo>last,String pid){
		List<AuthorityTree>trees=new ArrayList<AuthorityTree>();
		List<ResourcePo>_last=new ArrayList<ResourcePo>();
		for(ResourcePo resourcePo:last){
			if(resourcePo.getPid().equals(pid)){
				trees.add(this.buildAuthorityTree(resourcePo));
			}else{
				_last.add(resourcePo);
			}
		}
		last=_last;
		if(last.size()>0){
			for(AuthorityTree authorityTree:trees){
				authorityTree.setChildren(this.recursionChildern(last,authorityTree.getId()));
			}
			return trees;
		}
		return trees;
	}

	/**
	 *建造树型tree
	 * @param resourcePo
	 * @return
	 */
	private AuthorityTree buildAuthorityTree(ResourcePo resourcePo){
		if(resourcePo!=null){
			AuthorityTree authorityTree=new AuthorityTree();
			authorityTree.setId(resourcePo.getId());
			if(resourcePo.getPid()!=null){
				authorityTree.setPid(resourcePo.getPid());
			}
			if(StringUtil.isEmpty(resourcePo.getPname())){
				authorityTree.setPname(resourcePo.getPname());
			}
			authorityTree.setName(resourcePo.getName());
			authorityTree.setRsType(resourcePo.getRsType());
			authorityTree.setTarget(resourcePo.getTarget());
			authorityTree.setIcon(resourcePo.getIconCls());
			return authorityTree;
		}
		return null;
	}


}
