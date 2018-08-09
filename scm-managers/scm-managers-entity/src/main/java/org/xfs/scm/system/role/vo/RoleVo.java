package org.xfs.scm.system.role.vo;

import org.xfs.scm.system.role.entity.Role;

public class RoleVo extends Role {
	
	private Long userId;
	private int superFlag=0;
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
	public RoleVo() {
	}
	
	public RoleVo(Integer rid) {
		super.setRid(rid);
	}
}
