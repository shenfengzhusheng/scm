package org.xfs.scm.data.business.device.device.mapper;

import org.xfs.scm.data.business.device.device.entity.Device;
import org.xfs.scm.data.business.device.device.po.DevicePo;
import org.xfs.scm.data.business.device.device.vo.DeviceVo;

import java.util.List;

public interface DeviceMapper {
    int removeDevice(DeviceVo vo);

    int addDevice(DeviceVo record);

    List<DevicePo> getDevices(DeviceVo vo);

    int modifyDevice(DeviceVo record);

}