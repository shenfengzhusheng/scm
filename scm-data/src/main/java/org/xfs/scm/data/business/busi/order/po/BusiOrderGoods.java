package org.xfs.scm.data.business.busi.order.po;

import java.io.Serializable;

public class BusiOrderGoods implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5354853625504529061L;
	private Long id;
    private String orderId;

    private String goodsName;

    private Integer goodsType;

    private Integer goodsVolume;

    private Integer goodsWeight;

    private Integer vehicleType;

    private Integer vehicleLength;

    private Integer vehicleNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(Integer goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public Integer getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Integer goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(Integer vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public Integer getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        this.vehicleNum = vehicleNum;
    }
}