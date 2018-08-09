package org.xfs.scm.data.business.user.driver.driver.mapper;

import org.xfs.scm.data.business.user.driver.driver.bo.DriverBo;
import org.xfs.scm.data.business.user.driver.vehicle.vo.UserVehicleVo;

import java.util.List;

public interface DriverMapper {

    List<DriverBo> getUserVehicles(UserVehicleVo record);

}