package org.xfs.scm.system.resources.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.xfs.scm.base.entity.BaseEntity;

@Table(name="tb_resources")
public class Resources extends BaseEntity{
	public Resources(){}
	public Resources(String rsid) {
		super();
		this.rsid = rsid;
	}

	@Id
    private String rsid;

    private String pid;

    @Column(name="rs_type")
    private String rsType;

    private String name;

    private String url;

    private String iconcls;

    private Integer seq;

    private String target;

    private String memo;


    public String getRsid() {
        return rsid;
    }

    public void setRsid(String rsid) {
        this.rsid = rsid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType == null ? null : rsType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

   

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

   
}