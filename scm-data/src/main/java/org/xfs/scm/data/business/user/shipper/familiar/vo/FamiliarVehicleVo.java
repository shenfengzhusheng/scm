package org.xfs.scm.data.business.user.shipper.familiar.vo;

import org.xfs.scm.data.business.user.shipper.familiar.po.FamiliarVehicle;

import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.21:11
 * version:1.0
 */
public class FamiliarVehicleVo extends FamiliarVehicle {

    /**
	 * 
	 */
	private static final long serialVersionUID = 384933084973703999L;
	private String userHeadUrl;
    private String userNickName;
    private String userIdcardName;
    private String userPhone;
    private String vehicleNum;
    private Integer vehicleType;
    private String vehicleTypeName;
    private Integer vehicleLength;
    private Integer vehicleCapacity;
    private Integer vehicleVolume;

    private List<Long> shipperIds;

    public FamiliarVehicleVo(){}
    public FamiliarVehicleVo(Long id){
        super.setId(id);
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserIdcardName() {
        return userIdcardName;
    }

    public void setUserIdcardName(String userIdcardName) {
        this.userIdcardName = userIdcardName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(Integer vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public Integer getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(Integer vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public Integer getVehicleVolume() {
        return vehicleVolume;
    }

    public void setVehicleVolume(Integer vehicleVolume) {
        this.vehicleVolume = vehicleVolume;
    }

    public List<Long> getShipperIds() {
        return shipperIds;
    }

    public void setShipperIds(List<Long> shipperIds) {
        this.shipperIds = shipperIds;
    }
}
