package org.xfs.scm.data.business.basic.area.vo;

import org.xfs.scm.data.business.basic.area.entity.BdArea;

public class AreaVo extends BdArea{
	private String q;
	private int page;
	private int rows;
	public AreaVo(){}
	public AreaVo(Long areaId){
		super.setAreaId(areaId);
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
