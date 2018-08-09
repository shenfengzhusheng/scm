package org.xfs.scm.system.organization.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.xfs.scm.base.service.impl.BaseServiceImpl;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.system.organization.dao.OrganizationMapper;
import org.xfs.scm.system.organization.entity.Organization;
import org.xfs.scm.system.organization.po.OrganizationPo;
import org.xfs.scm.system.organization.service.OrganizationServiceI;
import org.xfs.scm.system.organization.vo.OrganizationVo;
import org.xfs.scm.util.BeanUtils;

import com.github.pagehelper.PageHelper;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationServiceI {
	
	@Resource OrganizationMapper organizationMapper;
	
	@Override
	public Boolean removeOrganization(OrganizationVo vo) {
		return null;
	}

	@Override
	public List<OrganizationPo> listOrganization(OrganizationVo vo) throws Exception {
		return this.organizationMapper.getOrganization(vo);
	}

	@Override
	public OrganizationPo getOrganization(OrganizationVo vo) throws Exception {
		List<OrganizationPo> list=this.listOrganization(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public boolean addOrganization(OrganizationVo vo) throws Exception {
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

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Grid grid(OrganizationVo vo, int page, int rows) throws Exception {
		Grid grid=new Grid();
		PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(page, rows);
		grid.setRows(this.organizationMapper.getOrganization(vo));
		grid.setTotal(this.organizationMapper.countAll(vo));
		List<OrganizationPo>list=grid.getRows();
		//grid.setList(null);
		System.out.println(JSON.toJSONString(list));
		/**
		 * 	PageInfo<OrganizationPo>pages=(PageInfo<OrganizationPo>)this.organizationMapper.getOrganization(vo);
		grid.setList(pages.getList());
		System.out.println(pages.getTotal());
		grid.setTotal(this.organizationMapper.countAll(vo));
		 */
		//PageInfo<OrganizationPo>pages=this.
		//pageHelper.offsetPage(offset, limit, count)
	//	grid.getList()
		return grid;
	}

	@Override
	public OrganizationPo getById(Long oid) throws Exception {
		return this.getOrganization(new OrganizationVo(oid));
	}
	
}
