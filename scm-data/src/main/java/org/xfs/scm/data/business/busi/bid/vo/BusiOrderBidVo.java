package org.xfs.scm.data.business.busi.bid.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_busi_order_bid")
public class BusiOrderBidVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5792884923075227829L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "driver_user_id")
    private Long driverUserId;

    @Column(name = "bid_price")
    private Integer bidPrice;

    @Column(name="bid_remark")
    private String bidRemark;

    @Column(name="bid_status")
    private Integer bidStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @Column(name="bid_expired_time")
    private Date bidExpiredTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @Column(name="create_time")
    private Date bidCreateTime;

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
        this.orderId = orderId;
    }

    public Long getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Long driverUserId) {
        this.driverUserId = driverUserId;
    }

    public Integer getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidRemark() {
        return bidRemark;
    }

    public void setBidRemark(String bidRemark) {
        this.bidRemark = bidRemark;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Date getBidExpiredTime() {
        return bidExpiredTime;
    }

    public void setBidExpiredTime(Date bidExpiredTime) {
        this.bidExpiredTime = bidExpiredTime;
    }

    public Date getBidCreateTime() {
        return bidCreateTime;
    }

    public void setBidCreateTime(Date bidCreateTime) {
        this.bidCreateTime = bidCreateTime;
    }

}
