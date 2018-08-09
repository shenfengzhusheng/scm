package org.xfs.scm.data.system.organization.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.data.system.organization.entity.Organization;


import org.xfs.scm.data.system.organization.mapper.OrganizationMapper;
import org.xfs.scm.data.system.organization.po.OrganizationPo;
import org.xfs.scm.data.system.organization.po.OrganizationTree;
import org.xfs.scm.data.system.organization.service.OrganizationServiceI;
import org.xfs.scm.data.system.organization.vo.OTree;
import org.xfs.scm.data.system.organization.vo.OrganizationVo;
import org.xfs.scm.platform.base.service.impl.BaseServiceImpl;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationServiceI {
	
	@Resource
	OrganizationMapper organizationMapper;
	
	@Override
	public Boolean removeOrganization(OrganizationVo vo) {
		return null;
	}

	@Override
	public List<OrganizationPo> listOrganization(OrganizationVo vo)  {
		return this.organizationMapper.getOrganization(vo);
	}

	@Override
	public OrganizationPo getOrganization(OrganizationVo vo)  {
		List<OrganizationPo> list=this.listOrganization(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public boolean addOrganization(OrganizationVo vo)  {
		if(vo!=null){
			Organization o=new Organization();
			BeanUtils.copyNotNullProperties(vo, o);
			if(super.saveSelective(o)==1){
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public Grid<OrganizationPo> grid(OrganizationVo vo, int page, int rows)  {
		Grid<OrganizationPo> grid=new Grid<OrganizationPo>();
		grid.setRows(this.organizationMapper.getOrganization(vo));
		grid.setTotal(this.organizationMapper.countAll(vo));

		return grid;
	}

	@Override
	public List<OTree<OrganizationPo>> getOrganizationTree(OrganizationVo vo)  {
		List<OTree<OrganizationPo>>tree=new ArrayList<OTree<OrganizationPo>>();
		List<OrganizationPo>data=this.listOrganization(vo);
		List<OrganizationPo>last=new ArrayList<OrganizationPo>();

		if(!data.isEmpty()){
			//1、查出一级机构
			for(OrganizationPo op: data){
				if(op.getPoid()!=null && op.getPoid()==0){
					tree.add(buildOTree(op));
				}else{
					last.add(op);
				}
			}
		}
		if(!last.isEmpty()){
			for(OTree<OrganizationPo> ot:tree){
				ot.setNodes(this.buildChildTree(last,ot.getId()));
			}
		}
		return tree;
	}

	@Override
	public List<OrganizationTree> tree(OrganizationVo vo) {
		List<OrganizationPo>list=this.listOrganization(vo);
		if(!list.isEmpty()){
			List<OrganizationPo>last=new ArrayList<>();

			List<OrganizationTree>trees=new ArrayList<>();
			//根结点
			for(OrganizationPo po:list){
				if(po.getPoid()==null){
					OrganizationTree tree=new OrganizationTree();
					BeanUtils.copyNotNullProperties(po,tree);
					trees.add(tree);
				}else if(po.getPoid().longValue()==0 ){
					OrganizationTree tree=new OrganizationTree();
					BeanUtils.copyNotNullProperties(po,tree);
					trees.add(tree);
				}else{
					last.add(po);
				}
			}
			//深度优先
			for(OrganizationTree tree:trees){
				tree.setChildren(this.treechildren(last,tree.getOid()));
			}
			return trees;
		}
		return null;
	}
	private  List<OrganizationTree> treechildren(List<OrganizationPo>last,Long poid){
		List<OrganizationTree> trees=new ArrayList<>();
		List<OrganizationPo>_last=new ArrayList<>();
		for (OrganizationPo po:last){
			if(po.getPoid()!=null && po.getPoid().longValue()==poid.longValue()){
				OrganizationTree tree=new OrganizationTree();
				BeanUtils.copyNotNullProperties(po,tree);
				trees.add(tree);
			}else{
				_last.add(po);
			}
		}
		if(_last.size()>0){
			for(OrganizationTree tree:trees){
				tree.setChildren(this.treechildren(_last,tree.getOid()));
			}
			return trees;
		}

		return trees;
	}

	private OTree<OrganizationPo> buildOTree(OrganizationPo op){
		OTree<OrganizationPo>ot=new OTree<OrganizationPo>();
		ot.setId(op.getOid());
		ot.setTitle(op.getOname());
		ot.setPid(op.getPoid());
		ot.setData(op);
		return ot;
	}
	private List<OTree<OrganizationPo>> buildChildTree(List<OrganizationPo>last,Long id){
		List<OTree<OrganizationPo>>childernTree=new ArrayList<OTree<OrganizationPo>>();
		List<OrganizationPo>_last=new ArrayList<OrganizationPo>();
		for(OrganizationPo op: last){
			if(id==op.getPoid().longValue()){
				childernTree.add(buildOTree(op));
			}else{
				_last.add(op);
			}
		}
		last=_last;
		if(last.size()>0){
			for(OTree<OrganizationPo> ot:childernTree){
				ot.setNodes(this.buildChildTree(last,ot.getId()));
			}
			return childernTree;
		}
		return childernTree;
	}

	@Override
	public OrganizationPo getById(Long oid)  {
		return this.getOrganization(new OrganizationVo(oid));
	}
	
}
