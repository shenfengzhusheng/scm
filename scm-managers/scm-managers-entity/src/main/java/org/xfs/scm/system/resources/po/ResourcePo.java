package org.xfs.scm.system.resources.po;

import java.io.Serializable;

public class ResourcePo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3560644648814356264L;

	private String pid;// 虚拟属性，用于获得当前资源的父资源ID

	private String id;
	private String name;
	private String pname;
	private String url;
	private String memo;
	private String iconCls;
	private Integer seq;
	private String target;
	private String rsType;
	public String getPid() {
		if(this.pid==null)
			return "";
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		if(this.pname==null){
			return "--";
		}
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMemo() {
		if(this.memo==null || "".equals(this.memo))
			return "--";
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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
		this.target = target;
	}
	public String getRsType() {
		return rsType;
	}
	public void setRsType(String rsType) {
		this.rsType= rsType;
	}
}
