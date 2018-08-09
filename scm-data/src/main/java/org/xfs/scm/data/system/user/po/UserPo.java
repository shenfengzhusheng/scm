package org.xfs.scm.data.system.user.po;

import org.xfs.scm.data.system.resources.entity.Resources;
import org.xfs.scm.data.system.role.entity.Role;
import org.xfs.scm.data.system.user.entity.User;

import java.util.List;


public class UserPo extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9107388983913958469L;
	private String ocode;
	private String oname;
	private String custName;
	private List<Resources>resources;
	
	private List<Role>roles;
	private int superFlag=0;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Override
	public String toString() {
		return "UserPo [ocode=" + ocode + ", oname=" + oname
				+ ", getUserId()=" + getUserId() + ", getCustId()=" + getCustId() + ", getOid()=" + getOid()
				+ ", getUserCode()=" + getUserCode() + ", getUserName()=" + getUserName() + ", getPwd()=" + getPwd()
				+ ", getState()=" + getState() + ", getEmail()=" + getEmail() + ", getSex()=" + getSex()
				+ ", getBirthday()=" + getBirthday() + ", getMobile()=" + getMobile() + ", toString()="
				+ super.toString() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedTime()=" + getCreatedTime()
				+ ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateTime()=" + getLastUpdateTime()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getSuperFlag() {
		return superFlag;
	}

	public void setSuperFlag(int superFlag) {
		this.superFlag = superFlag;
	}
}
