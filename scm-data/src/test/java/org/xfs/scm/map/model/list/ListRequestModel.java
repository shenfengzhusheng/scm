package org.xfs.scm.map.model.list;

import org.xfs.scm.map.model.base.BaseMapModel;

public class ListRequestModel extends BaseMapModel{
    private String filter;
    private String coord_type_output;
    private int page_index=1;
    private int page_size=10;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCoord_type_output() {
        return coord_type_output;
    }

    public void setCoord_type_output(String coord_type_output) {
        this.coord_type_output = coord_type_output;
    }

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
}
