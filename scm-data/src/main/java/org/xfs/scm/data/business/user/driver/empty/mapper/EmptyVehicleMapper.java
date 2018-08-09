package org.xfs.scm.data.business.user.driver.empty.mapper;

import org.xfs.scm.data.business.user.driver.empty.vo.EmptyVehicleVo;

import java.util.List;

public interface EmptyVehicleMapper {
    int removeEmptyVehicle(EmptyVehicleVo record);


    int addEmptyVehicle(EmptyVehicleVo record);

    List<EmptyVehicleVo> getEmptyVehicles(EmptyVehicleVo record);

    int modifyEmptyVehicle(EmptyVehicleVo record);
}