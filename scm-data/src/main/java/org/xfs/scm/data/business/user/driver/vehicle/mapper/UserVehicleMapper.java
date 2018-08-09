package org.xfs.scm.data.business.user.driver.vehicle.mapper;

import org.xfs.scm.data.business.user.driver.vehicle.vo.UserVehicleVo;

import java.util.List;

public interface UserVehicleMapper {
    int  removeUserVehicle(UserVehicleVo record);

    int addUserVehicle(UserVehicleVo record);

    List<UserVehicleVo> getUserVehicles(UserVehicleVo record);

    int modifyUserVehicle(UserVehicleVo record);

}