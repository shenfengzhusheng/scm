package org.xfs.scm.data.system.role_resources.vo;


import javax.validation.constraints.NotNull;
import java.util.List;

public class GrantVo {

    @NotNull(message = "角色ID不能为能空！")
    private Integer rid;


    private List<String> ids;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
