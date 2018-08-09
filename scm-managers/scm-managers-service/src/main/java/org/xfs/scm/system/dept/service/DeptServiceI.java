package org.xfs.scm.system.dept.service;

import java.util.List;

import org.xfs.scm.base.service.BaseServiceI;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.system.dept.entity.Dept;
import org.xfs.scm.system.dept.po.DeptPo;
import org.xfs.scm.system.dept.vo.DeptVo;

public interface DeptServiceI extends BaseServiceI<Dept> {
	
	Boolean removeDept(DeptVo vo);
	
	
	List<DeptPo> list(DeptVo vo);
	
	DeptPo getDept(DeptVo vo);
	
	DeptPo getById(Long deptId);
	
	boolean addDept(DeptVo vo);


	
	boolean modifyDept(DeptVo vo);
	

	
	Grid<DeptPo> grid(DeptVo vo, int page, int rows);
}
