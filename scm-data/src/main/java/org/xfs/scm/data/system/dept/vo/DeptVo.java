package org.xfs.scm.data.system.dept.vo;


import org.xfs.scm.data.system.dept.entity.Dept;

public class DeptVo extends Dept {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1105622217741228955L;
	private String q;
	public DeptVo() {
	}
	
	public DeptVo(Long deptId) {
		super.setDeptId(deptId);
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
}
