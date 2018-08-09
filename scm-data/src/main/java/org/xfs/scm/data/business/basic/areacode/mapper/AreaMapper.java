package org.xfs.scm.data.business.basic.areacode.mapper;

import org.xfs.scm.data.business.basic.areacode.po.Area;
import org.xfs.scm.data.business.basic.areacode.vo.AreaVo;

import java.util.List;

public interface AreaMapper {

    int removeArea(Area record);

    int addArea(Area record);

    List<Area> getAreas(AreaVo record);

    int modifyArea(Area record);
}