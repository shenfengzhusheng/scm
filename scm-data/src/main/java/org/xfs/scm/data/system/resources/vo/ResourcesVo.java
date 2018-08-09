package org.xfs.scm.data.system.resources.vo;


import org.xfs.scm.data.system.resources.entity.Resources;

public class ResourcesVo extends Resources {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3353995594336018803L;
	private Integer rid;
	private Long userId;
	
	private int superFlag=0;
	
	public ResourcesVo(){}
	public ResourcesVo(String rsid){
		super.setRsid(rsid);
	}

	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
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
}
