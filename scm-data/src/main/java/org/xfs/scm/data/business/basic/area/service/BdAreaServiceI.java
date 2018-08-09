package org.xfs.scm.data.business.basic.area.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.area.entity.BdArea;
import org.xfs.scm.data.business.basic.area.vo.AreaVo;

import java.util.List;



public interface BdAreaServiceI {
	Grid<BdArea> grid(AreaVo vo);
	
	int add(AreaVo vo);

	int remove(AreaVo vo);

	int modify(AreaVo vo);

	BdArea get(AreaVo vo);
	
	List<BdArea>find(AreaVo vo);
}
