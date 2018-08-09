package org.xfs.scm.system.organization.po;

import org.xfs.scm.system.organization.entity.Organization;

public class OrganizationPo extends Organization {
	  private String pname;

	public String getPname() {
		if(this.pname==null)
			return "";
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "OrganizationPo [pname=" + pname + ", getOid()=" + getOid() + ", getPoid()=" + getPoid()
				+ ", getOcode()=" + getOcode() + ", getOname()=" + getOname() + ", getIndependence()="
				+ getIndependence() + ", getState()=" + getState() + ", getMemo()=" + getMemo() + ", getAddress()="
				+ getAddress() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedTime()=" + getCreatedTime()
				+ ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateTime()=" + getLastUpdateTime()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
