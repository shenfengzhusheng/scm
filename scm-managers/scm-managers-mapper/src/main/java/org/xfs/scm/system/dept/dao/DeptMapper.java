package org.xfs.scm.system.dept.dao;

import java.util.List;

import org.xfs.scm.system.dept.entity.Dept;
import org.xfs.scm.system.dept.po.DeptPo;
import org.xfs.scm.system.dept.vo.DeptVo;

import com.github.abel533.mapper.Mapper;

public interface DeptMapper extends Mapper<Dept> {
	List<DeptPo> getDept(DeptVo vo);
	
	Long countAll(DeptVo vo);
	
	
	int removeDept(DeptVo vo);
}