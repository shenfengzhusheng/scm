package org.xfs.scm.business.bd.area.dao;

import java.util.List;

import org.xfs.scm.business.bd.area.entity.Area;
import org.xfs.scm.business.bd.area.vo.AreaVo;



public interface AreaMapper {

    int removeArea(AreaVo record);

    int addArea(AreaVo record);

    List<Area> getAreas(AreaVo record);

    int modifyArea(AreaVo record);
}