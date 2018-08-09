package org.xfs.scm.data.business.crm.user_customer.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GrantUserCustomerVo implements Serializable {
    @NotNull(message = "用户未选择!")
    private Long userId;
    @NotNull(message = "没有需要授权的客户！")
    private List<Long> custIds;

    private String userName;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCustIds() {
        return custIds;
    }

    public void setCustIds(List<Long> custIds) {
        this.custIds = custIds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
