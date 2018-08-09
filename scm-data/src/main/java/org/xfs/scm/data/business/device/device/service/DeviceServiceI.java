package org.xfs.scm.data.business.device.device.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.device.device.entity.Device;
import org.xfs.scm.data.business.device.device.po.DevicePo;
import org.xfs.scm.data.business.device.device.vo.DeviceVo;

import java.util.List;

public interface DeviceServiceI {

    int remove(DeviceVo vo);

    int add(DeviceVo vo);

    List<DevicePo> find(DeviceVo vo);

    Grid<DevicePo> grid(DeviceVo vo);

    DevicePo get(DeviceVo vo);


    int modify(DeviceVo vo);
}
