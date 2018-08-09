package org.xfs.scm.system.dept.vo;

import org.xfs.scm.system.dept.entity.Dept;

public class DeptVo extends Dept {
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
