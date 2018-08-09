package org.xfs.scm.data.business.user.driver.vehicle.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserVehicle implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1154450084301230943L;

	private Long id;

    private Long driverUserId;

    private String driverLicenseNum;

    private String driverLicenseUrl;

    private Integer driverLicenseAudit;

    private String auditRemark;

    private Long auditMid;

    private String vechileLisenseUrl;

    private Integer vehicleType;

    private String vehicleOwnName;

    private String vehicleNum;

    private Integer vehicleLength;

    private Integer vehicleCapacity;

    private Integer vehicleVolume;

    private Integer vehicleCarriageLength;

    private Integer vehicleCarriageWidth;

    private Integer vehicleCarriageHigh;

    private Double lastLongitude;

    private Double lastLatitude;

    private String lastAddress;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date lastUpdateTime;

    private Integer vehicleStatus;
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

    public Long getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Long driverUserId) {
        this.driverUserId = driverUserId;
    }

    public String getDriverLicenseNum() {
        return driverLicenseNum;
    }

    public void setDriverLicenseNum(String driverLicenseNum) {
        this.driverLicenseNum = driverLicenseNum == null ? null : driverLicenseNum.trim();
    }

    public String getDriverLicenseUrl() {
        return driverLicenseUrl;
    }

    public void setDriverLicenseUrl(String driverLicenseUrl) {
        this.driverLicenseUrl = driverLicenseUrl == null ? null : driverLicenseUrl.trim();
    }

    public Integer getDriverLicenseAudit() {
        return driverLicenseAudit;
    }

    public void setDriverLicenseAudit(Integer driverLicenseAudit) {
        this.driverLicenseAudit = driverLicenseAudit;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public Long getAuditMid() {
        return auditMid;
    }

    public void setAuditMid(Long auditMid) {
        this.auditMid = auditMid;
    }

    public String getVechileLisenseUrl() {
        return vechileLisenseUrl;
    }

    public void setVechileLisenseUrl(String vechileLisenseUrl) {
        this.vechileLisenseUrl = vechileLisenseUrl == null ? null : vechileLisenseUrl.trim();
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleOwnName() {
        return vehicleOwnName;
    }

    public void setVehicleOwnName(String vehicleOwnName) {
        this.vehicleOwnName = vehicleOwnName == null ? null : vehicleOwnName.trim();
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum == null ? null : vehicleNum.trim();
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

    public Integer getVehicleCarriageLength() {
        return vehicleCarriageLength;
    }

    public void setVehicleCarriageLength(Integer vehicleCarriageLength) {
        this.vehicleCarriageLength = vehicleCarriageLength;
    }

    public Integer getVehicleCarriageWidth() {
        return vehicleCarriageWidth;
    }

    public void setVehicleCarriageWidth(Integer vehicleCarriageWidth) {
        this.vehicleCarriageWidth = vehicleCarriageWidth;
    }

    public Integer getVehicleCarriageHigh() {
        return vehicleCarriageHigh;
    }

    public void setVehicleCarriageHigh(Integer vehicleCarriageHigh) {
        this.vehicleCarriageHigh = vehicleCarriageHigh;
    }

    public Double getLastLongitude() {
        return lastLongitude;
    }

    public void setLastLongitude(Double lastLongitude) {
        this.lastLongitude = lastLongitude;
    }

    public Double getLastLatitude() {
        return lastLatitude;
    }

    public void setLastLatitude(Double lastLatitude) {
        this.lastLatitude = lastLatitude;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress == null ? null : lastAddress.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Integer vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
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