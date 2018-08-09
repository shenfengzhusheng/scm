package org.xfs.scm.data.business.user.shipper.shipper.mapper;


import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;

import java.util.List;

public interface ShipperMapper {
    int removeShipper(ShipperVo record);


    int addShipper(ShipperVo record);

    List<ShipperBo> getShippers(ShipperVo record);

    int modifyShippers(ShipperVo record);

}