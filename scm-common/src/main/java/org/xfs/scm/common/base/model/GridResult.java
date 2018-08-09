package org.xfs.scm.common.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.9:18
 * version:1.0
 */
public class GridResult<T> implements Serializable {

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
