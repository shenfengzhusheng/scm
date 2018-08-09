package org.xfs.scm.business.bd.area.vo;

import org.xfs.scm.business.bd.area.entity.Area;

public class AreaVo  extends Area{

	/**
	 * 
	 */
	private static final long serialVersionUID = 326167582325776254L;
	private String areaLevelWithOpt;
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getAreaLevelWithOpt() {
		return areaLevelWithOpt;
	}

	public void setAreaLevelWithOpt(String areaLevelWithOpt) {
		this.areaLevelWithOpt = areaLevelWithOpt;
	}
}
