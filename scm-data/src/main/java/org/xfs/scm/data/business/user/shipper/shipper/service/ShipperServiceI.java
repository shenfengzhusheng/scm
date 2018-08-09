package org.xfs.scm.data.business.user.shipper.shipper.service;

import org.xfs.scm.data.business.user.shipper.shipper.bo.ShipperBo;
import org.xfs.scm.data.business.user.shipper.shipper.vo.ShipperVo;

import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.21:27
 * version:1.0
 */
public interface ShipperServiceI {

    ShipperBo addShipperBo(ShipperBo bo);

    List<ShipperBo> getShippers(ShipperVo vo);

    ShipperBo getShipper(ShipperVo vo);

}
