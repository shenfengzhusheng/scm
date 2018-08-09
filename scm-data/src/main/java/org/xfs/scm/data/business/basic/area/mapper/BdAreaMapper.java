package org.xfs.scm.data.business.basic.area.mapper;

import org.xfs.scm.data.business.basic.area.entity.BdArea;
import org.xfs.scm.data.business.basic.area.vo.AreaVo;

import java.util.List;



public interface BdAreaMapper {
	
    int removeArea(AreaVo vo);

    int addArea(AreaVo vo);

    int modifyArea(AreaVo vo);
    
    List<BdArea> getAreas(AreaVo vo);
    
}