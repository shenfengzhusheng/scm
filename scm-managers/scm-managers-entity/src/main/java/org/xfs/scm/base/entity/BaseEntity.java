package org.xfs.scm.base.entity;

import javax.persistence.Column;
import java.util.Date;

/**
 * 通用mybatis entity
 * Created by 神风逐胜 on 2017/9/24 0024.14:54
 * version:1.0
 */
public class BaseEntity {

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_time")
    private Date createdTime;

    @Column(name="last_update_by")
    private String lastUpdateBy;

    @Column(name="last_update_time")
    private Date lastUpdateTime;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
