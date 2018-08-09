package org.xfs.scm.data.business.user.driver.line.mapper;

import org.xfs.scm.data.business.user.driver.line.vo.DriverLinerVo;

import java.util.List;

public interface DriverLinerMapper {
    int removeDriverLiner(DriverLinerVo record);

    int addDriverLiner(DriverLinerVo record);

    List<DriverLinerVo> getDriverLiners(DriverLinerVo record);

    int modfiygetDriverLiner(DriverLinerVo record);

}