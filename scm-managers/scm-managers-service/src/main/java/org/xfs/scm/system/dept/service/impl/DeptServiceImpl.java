package org.xfs.scm.system.dept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.base.service.impl.BaseServiceImpl;
import org.xfs.scm.system.dept.dao.DeptMapper;
import org.xfs.scm.system.dept.entity.Dept;
import org.xfs.scm.system.dept.po.DeptPo;
import org.xfs.scm.system.dept.service.DeptServiceI;
import org.xfs.scm.system.dept.vo.DeptVo;
import org.xfs.scm.util.BeanUtils;

import com.github.pagehelper.PageHelper;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptServiceI {
	
	@Resource DeptMapper deptMapper;
	
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


	@SuppressWarnings("static-access")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Grid<DeptPo> grid(DeptVo vo, int page, int rows) {
		Grid<DeptPo> grid=new Grid<DeptPo>();
		PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(page, rows, false);
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
