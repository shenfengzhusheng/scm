package org.xfs.scm.data.business.basic.areacode.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.areacode.po.Area;
import org.xfs.scm.data.business.basic.areacode.vo.AreaVo;

import java.util.List;

public interface AreaServiceI {

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int removeArea(Area record);

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int addArea(Area record);

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    List<Area> getAreas(AreaVo record);
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    Area getArea(AreaVo record);
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    Grid<Area> grid(AreaVo record, Integer page, Integer rows);

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int modifyArea(Area record);
}
