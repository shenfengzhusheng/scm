package org.xfs.scm.item.vo;

import org.xfs.scm.item.entity.Item;

public class ItemVo extends Item {
	/**
	 * 第几页
	 */
	private int page=1;
	/**
	 * 默认查询记录数
	 */
	private int rows=10;
	@SuppressWarnings("unused")
	
	/**
	 * 起始条目数
	 */
	private int start;
	public ItemVo() {
	}
	public ItemVo(Long id) {
		super.setId(id);
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
	public int getStart() {
		return (this.page-1)*this.rows;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
