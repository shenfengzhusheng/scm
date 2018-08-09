package org.xfs.scm.data.system.organization.vo;


import org.xfs.scm.data.system.organization.entity.Organization;

public class OrganizationVo extends Organization {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3454346084835785882L;
	private String q; 
	private String pname;
	private int superFlag=0;
	private Long userId;
	private boolean isEasyui=false;
	public OrganizationVo(){}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getSuperFlag() {
		return superFlag;
	}

	public void setSuperFlag(int superFlag) {
		this.superFlag = superFlag;
	}

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

	public boolean isEasyui() {
		return isEasyui;
	}

	public void setEasyui(boolean easyui) {
		isEasyui = easyui;
	}
}
