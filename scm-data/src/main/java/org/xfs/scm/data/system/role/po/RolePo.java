package org.xfs.scm.data.system.role.po;


import org.xfs.scm.data.system.role.entity.Role;

public class RolePo extends Role {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6577023769564769915L;
	private String deptCode;
	private String deptName;
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
}
