package org.xfs.scm.platform.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/8 0008.21:38
 * version:1.0
 */
public class Grid<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2462194582231032737L;
	private List<T> rows;
    private Long total;

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
