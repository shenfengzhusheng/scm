package org.xfs.scm.data.business.user.shipper.contact.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BusiContact implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4023518191640139746L;

	private Long id;

    private String orderId;

    private Long ownerUserId;

    private String contactName;

    private String contactPhone;

    protected String contactAddrCode;

    private String contactAddrDetail;

    private Integer contactDefault;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactAddrCode() {
        return contactAddrCode;
    }

    public void setContactAddrCode(String contactAddrCode) {
        this.contactAddrCode = contactAddrCode == null ? null : contactAddrCode.trim();
    }

    public String getContactAddrDetail() {
        return contactAddrDetail;
    }

    public void setContactAddrDetail(String contactAddrDetail) {
        this.contactAddrDetail = contactAddrDetail == null ? null : contactAddrDetail.trim();
    }

    public Integer getContactDefault() {
        return contactDefault;
    }

    public void setContactDefault(Integer contactDefault) {
        this.contactDefault = contactDefault;
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