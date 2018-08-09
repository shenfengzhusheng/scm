package org.xfs.scm.data.business.user.driver.line.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DriverLiner implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5908695185549372472L;

	private Long id;

    private Long driverUserId;

    private String addrAreaCode;

    private String addrAreaName;

    private Integer isDefault;

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

    public Long getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Long driverUserId) {
        this.driverUserId = driverUserId;
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

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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