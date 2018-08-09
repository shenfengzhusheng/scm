package org.xfs.scm.data.system.dept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.data.system.dept.entity.Dept;
import org.xfs.scm.data.system.dept.mapper.DeptMapper;
import org.xfs.scm.data.system.dept.po.DeptPo;
import org.xfs.scm.data.system.dept.service.DeptServiceI;
import org.xfs.scm.data.system.dept.vo.DeptVo;
import org.xfs.scm.platform.base.service.impl.BaseServiceImpl;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptServiceI {
	
	@Resource
	private DeptMapper deptMapper;
	
	@Override
	public Boolean removeDept(DeptVo vo) {
		return this.deptMapper.removeDept(vo)==1;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<DeptPo> list(DeptVo vo) {
		return this.deptMapper.getDept(vo);
	}
	
	@Override
	public DeptPo getDept(DeptVo vo) {
		List<DeptPo> list=this.list(vo);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public DeptPo getById(Long deptId) {
		return this.getDept(new DeptVo(deptId));
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public boolean addDept(DeptVo vo) {
		if(vo!=null){
			vo.setDeptId(null);
			Dept o=new Dept();
			BeanUtils.copyNotNullProperties(vo, o);
			if(this.saveSelective(o)==1)
				return true;
			return false;
		}
		return false;
	}


	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Grid<DeptPo> grid(DeptVo vo, int page, int rows) {
		Grid<DeptPo> grid=new Grid<DeptPo>();
		grid.setRows(this.deptMapper.getDept(vo));
		grid.setTotal(this.deptMapper.countAll(vo));
		return grid;
	}

	@Override
	public boolean modifyDept(DeptVo vo) {
		if(vo!=null){
			Dept o=new Dept();
			BeanUtils.copyNotNullProperties(vo, o);
			if(this.updateByIdSelective(o)==1)
				return true;
			return false;
		}
		return false;
	}
}
