package org.xfs.scm.data.business.busi.log.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_busi_order_log")
public class BusiOrderLogVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1727012473628908335L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_id")
    private String orderId;

    @Column(name="order_status_from")
    private Integer orderStatusFrom;

    @Column(name="order_status_to")
    private Integer orderStatusTo;

    @Column
    private String remark;

    @Column(name = "op_type")
    private Integer opType;

    @Column(name = "op_user_id")
    private Long opUserId;

    @Column(name = "op_mid")
    private Long opMid;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getOrderStatusFrom() {
        return orderStatusFrom;
    }

    public void setOrderStatusFrom(Integer orderStatusFrom) {
        this.orderStatusFrom = orderStatusFrom;
    }

    public Integer getOrderStatusTo() {
        return orderStatusTo;
    }

    public void setOrderStatusTo(Integer orderStatusTo) {
        this.orderStatusTo = orderStatusTo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Long opUserId) {
        this.opUserId = opUserId;
    }

    public Long getOpMid() {
        return opMid;
    }

    public void setOpMid(Long opMid) {
        this.opMid = opMid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
