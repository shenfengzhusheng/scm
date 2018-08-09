package org.xfs.scm.common.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grid<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 对象列表
	 */
	private List<T> rows;
	
	/*
	 * 列表总数
	 */
	private Long total;

	public Grid(){
		this.rows=new ArrayList<>();
		this.total=0L;
	}


	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}
