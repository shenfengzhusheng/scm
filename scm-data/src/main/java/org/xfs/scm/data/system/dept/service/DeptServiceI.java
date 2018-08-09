package org.xfs.scm.data.system.dept.service;

import java.util.List;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.system.dept.entity.Dept;
import org.xfs.scm.data.system.dept.po.DeptPo;
import org.xfs.scm.data.system.dept.vo.DeptVo;
import org.xfs.scm.platform.base.service.BaseServiceI;


public interface DeptServiceI extends BaseServiceI<Dept> {
	
	Boolean removeDept(DeptVo vo);
	
	
	List<DeptPo> list(DeptVo vo);
	
	DeptPo getDept(DeptVo vo);
	
	DeptPo getById(Long deptId);
	
	boolean addDept(DeptVo vo);


	
	boolean modifyDept(DeptVo vo);
	

	
	Grid<DeptPo> grid(DeptVo vo, int page, int rows);
}
