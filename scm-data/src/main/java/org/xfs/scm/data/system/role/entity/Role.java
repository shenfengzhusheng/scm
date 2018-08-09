package org.xfs.scm.data.system.role.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.xfs.scm.platform.base.model.BaseEntity;

@Table(name="tb_role")
public class Role extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4872570962387061120L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
	
	@Column(name="dept_id")
    private Long deptId;

    private String rname;

    private Integer seq;

    private Byte state;

    private String memo;

   
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    
}