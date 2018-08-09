package org.xfs.scm.data.business.busi.order.vo;

import org.xfs.scm.data.business.busi.order.po.BusiOrderGoods;

public class BusiOrderGoodsVo  extends BusiOrderGoods {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9203263685755190765L;

	private String goodsTypeName;

    private String vehicleTypeName;

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
}
