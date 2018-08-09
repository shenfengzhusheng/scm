package org.xfs.scm.data.business.user.driver.empty.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class EmptyVehicle implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8767450038664716667L;

	private Long id;

    private Long linerId;

    private Long driverUserId;

    private Integer linerType;

    private Integer shareType;

    private String addrAreaCode;

    private String addrAreaName;

    private Integer leftSpacePer;

    private Integer vehicleType;

    private Integer vehicleLength;

    private Integer emptyVehicleStatus;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date startTime;

    private String remark;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLinerId() {
        return linerId;
    }

    public void setLinerId(Long linerId) {
        this.linerId = linerId;
    }

    public Long getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Long driverUserId) {
        this.driverUserId = driverUserId;
    }

    public Integer getLinerType() {
        return linerType;
    }

    public void setLinerType(Integer linerType) {
        this.linerType = linerType;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public String getAddrAreaCode() {
        return addrAreaCode;
    }

    public void setAddrAreaCode(String addrAreaCode) {
        this.addrAreaCode = addrAreaCode == null ? null : addrAreaCode.trim();
    }

    public String getAddrAreaName() {
        return addrAreaName;
    }

    public void setAddrAreaName(String addrAreaName) {
        this.addrAreaName = addrAreaName == null ? null : addrAreaName.trim();
    }

    public Integer getLeftSpacePer() {
        return leftSpacePer;
    }

    public void setLeftSpacePer(Integer leftSpacePer) {
        this.leftSpacePer = leftSpacePer;
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

    public Integer getEmptyVehicleStatus() {
        return emptyVehicleStatus;
    }

    public void setEmptyVehicleStatus(Integer emptyVehicleStatus) {
        this.emptyVehicleStatus = emptyVehicleStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}