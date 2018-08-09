package org.xfs.scm.data.system.dept.mapper;

import java.util.List;



import com.github.abel533.mapper.Mapper;
import org.xfs.scm.data.system.dept.entity.Dept;
import org.xfs.scm.data.system.dept.po.DeptPo;
import org.xfs.scm.data.system.dept.vo.DeptVo;

public interface DeptMapper extends Mapper<Dept> {
	List<DeptPo> getDept(DeptVo vo);
	
	Long countAll(DeptVo vo);

	int removeDept(DeptVo vo);
}