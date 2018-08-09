package org.xfs.scm.system.user.po;

import java.util.List;

import org.xfs.scm.system.user.entity.User;

public class UserPo extends User {
	private String ocode;
	private String oname;
	private String deptCode;
	private String deptName;
	private List<String>resourceList;
	
	private String birthdayText;

	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "UserPo [ocode=" + ocode + ", oname=" + oname + ", deptCode=" + deptCode + ", deptName=" + deptName
				+ ", getUserId()=" + getUserId() + ", getDeptId()=" + getDeptId() + ", getOid()=" + getOid()
				+ ", getUserCode()=" + getUserCode() + ", getUserName()=" + getUserName() + ", getPwd()=" + getPwd()
				+ ", getState()=" + getState() + ", getEmail()=" + getEmail() + ", getSex()=" + getSex()
				+ ", getBirthday()=" + getBirthday() + ", getMobile()=" + getMobile() + ", toString()="
				+ super.toString() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedTime()=" + getCreatedTime()
				+ ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateTime()=" + getLastUpdateTime()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	public String getBirthdayText() {
		return birthdayText;
	}
	public void setBirthdayText(String birthdayText) {
		this.birthdayText = birthdayText;
	}
	
}
