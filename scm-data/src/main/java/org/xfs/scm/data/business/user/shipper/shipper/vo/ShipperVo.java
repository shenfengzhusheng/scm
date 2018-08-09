package org.xfs.scm.data.business.user.shipper.shipper.vo;

import org.xfs.scm.data.business.user.shipper.shipper.po.Shipper;

import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.20:44
 * version:1.0
 */
public class ShipperVo extends Shipper {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8390256205282140165L;
	/**
     * 货主id
     */
    private List<Long> shipperIds;

    public List<Long> getShipperIds() {
        return shipperIds;
    }

    public void setShipperIds(List<Long> shipperIds) {
        this.shipperIds = shipperIds;
    }

}
