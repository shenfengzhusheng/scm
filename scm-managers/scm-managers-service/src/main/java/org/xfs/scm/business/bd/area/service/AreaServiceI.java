package org.xfs.scm.business.bd.area.service;


import org.xfs.scm.business.bd.area.entity.Area;
import org.xfs.scm.business.bd.area.vo.AreaVo;
import org.xfs.scm.common.base.model.Grid;


import java.util.List;

public interface AreaServiceI {

    int removeArea(AreaVo record);

    int addArea(AreaVo record);

    List<Area> getAreas(AreaVo record);
    Area getArea(AreaVo record);
    Grid<Area> grid(AreaVo record, Integer page, Integer rows);

    int modifyArea(AreaVo record);
}
