package org.xfs.scm.common.base.vo;

import java.io.Serializable;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.14:52
 * version:1.0
 */
public class BaseVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6232873161577344268L;
	/**
     * 第几页
     */
    private int page=1;
    /**
     * 默认查询记录数
     */
    private int rows=10;
    /**
     * 起始条目数
     */
    @SuppressWarnings("unused")
    private int start;
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
