package org.xfs.scm.data.business.user.account.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1361007124249723099L;

	private Long userId;

    private String userNickName;

    private Integer userSex;

    private String userHeaderUrl;

    private String userBirth;

    private String userEmail;

    private String userAddr;

    private String userAddrDetail;

    private String userIdcardName;

    private String userIdcardNum;

    private String userIdcardBackUrl;

    private String userIdcardFrontUrl;

    private Integer userStatus;

    private String auditRemark;

    private Long auditMid;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserHeaderUrl() {
        return userHeaderUrl;
    }

    public void setUserHeaderUrl(String userHeaderUrl) {
        this.userHeaderUrl = userHeaderUrl == null ? null : userHeaderUrl.trim();
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth == null ? null : userBirth.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr == null ? null : userAddr.trim();
    }

    public String getUserAddrDetail() {
        return userAddrDetail;
    }

    public void setUserAddrDetail(String userAddrDetail) {
        this.userAddrDetail = userAddrDetail == null ? null : userAddrDetail.trim();
    }

    public String getUserIdcardName() {
        return userIdcardName;
    }

    public void setUserIdcardName(String userIdcardName) {
        this.userIdcardName = userIdcardName == null ? null : userIdcardName.trim();
    }

    public String getUserIdcardNum() {
        return userIdcardNum;
    }

    public void setUserIdcardNum(String userIdcardNum) {
        this.userIdcardNum = userIdcardNum == null ? null : userIdcardNum.trim();
    }

    public String getUserIdcardBackUrl() {
        return userIdcardBackUrl;
    }

    public void setUserIdcardBackUrl(String userIdcardBackUrl) {
        this.userIdcardBackUrl = userIdcardBackUrl == null ? null : userIdcardBackUrl.trim();
    }

    public String getUserIdcardFrontUrl() {
        return userIdcardFrontUrl;
    }

    public void setUserIdcardFrontUrl(String userIdcardFrontUrl) {
        this.userIdcardFrontUrl = userIdcardFrontUrl == null ? null : userIdcardFrontUrl.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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