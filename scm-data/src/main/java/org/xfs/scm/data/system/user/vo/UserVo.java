package org.xfs.scm.data.system.user.vo;


import org.xfs.scm.data.system.user.entity.User;


public class UserVo extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6318718485404074908L;
	private String ocode;
	private String deptCode;
	private Long rid;
	
	private String birthdayText;
	
	public UserVo(){}
	public UserVo(Long userId){
		super.setUserId(userId);
	}
	private int superFlag=0;

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

		return birthdayText;
	}
	public void setBirthdayText(String birthdayText) {
		this.birthdayText = birthdayText;
	}

}
