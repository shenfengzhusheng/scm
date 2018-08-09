package org.xfs.scm.system.dept.po;

import org.xfs.scm.system.dept.entity.Dept;

public class DeptPo extends Dept {
	private Long pdeptId;
	
	private String pcode;	    
	    
    private String pname;
    
    private String ocode;
    
    private String oname;

	public Long getPdeptId() {
		return pdeptId;
	}

	public void setPdeptId(Long pdeptId) {
		this.pdeptId = pdeptId;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

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

	@Override
	public String toString() {
		return "DeptPo [pdeptId=" + pdeptId + ", pcode=" + pcode + ", pname=" + pname + ", ocode=" + ocode + ", oname="
				+ oname + ", getDeptId()=" + getDeptId() + ", getpDeptId()=" + getpDeptId() + ", getOid()=" + getOid()
				+ ", getDeptCode()=" + getDeptCode() + ", getDeptName()=" + getDeptName() + ", getChanger()="
				+ getChanger() + ", getCtype()=" + getCtype() + ", getState()=" + getState() + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedTime()=" + getCreatedTime() + ", getLastUpdateBy()=" + getLastUpdateBy()
				+ ", getLastUpdateTime()=" + getLastUpdateTime() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
