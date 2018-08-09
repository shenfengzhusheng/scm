package org.xfs.scm.system.organization.vo;

import org.xfs.scm.system.organization.entity.Organization;

public class OrganizationVo extends Organization {
	private String q; 
	private String pname;
	public OrganizationVo(){}
	public OrganizationVo(Long oid){
		super.setOid(oid);
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
