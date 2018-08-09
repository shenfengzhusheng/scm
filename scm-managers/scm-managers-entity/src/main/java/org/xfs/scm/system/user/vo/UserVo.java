package org.xfs.scm.system.user.vo;

import org.xfs.scm.system.user.entity.User;

public class UserVo extends User {
	
	private Long oid;
	private String ocode;
	private String deptCode;
	private Long rid;
	
	private String BirthdayText;
	
	public UserVo(){}
	public UserVo(Long userId){
		super.setUserId(userId);
	}
	
	private int superFlag=0;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public int getSuperFlag() {
		return superFlag;
	}
	public void setSuperFlag(int superFlag) {
		this.superFlag = superFlag;
	}
	public String getBirthdayText() {
		return BirthdayText;
	}
	public void setBirthdayText(String birthdayText) {
		BirthdayText = birthdayText;
	}

}
