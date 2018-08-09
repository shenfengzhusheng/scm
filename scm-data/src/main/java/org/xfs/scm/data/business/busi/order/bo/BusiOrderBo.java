package org.xfs.scm.data.business.busi.order.bo;

import org.xfs.scm.data.business.busi.appoint.vo.BusiOrderAppointVo;
import org.xfs.scm.data.business.busi.bid.vo.BusiOrderBidVo;
import org.xfs.scm.data.business.busi.contact.vo.BusiOrderContactVo;
import org.xfs.scm.data.business.busi.log.vo.BusiOrderLogVo;
import org.xfs.scm.data.business.busi.order.po.BusiOrder;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderGoodsVo;

import java.util.List;

public class BusiOrderBo extends BusiOrder {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2031735766116995704L;
	private BusiOrderContactVo contact;
    private List<BusiOrderGoodsVo> goods;
    private List<BusiOrderLogVo> logs;
    private BusiOrderAppointVo appoint;
    private List<BusiOrderBidVo> bids;

    public BusiOrderContactVo getContact() {
        return contact;
    }

    public void setContact(BusiOrderContactVo contact) {
        this.contact = contact;
    }

    public List<BusiOrderGoodsVo> getGoods() {
        return goods;
    }

    public void setGoods(List<BusiOrderGoodsVo> goods) {
        this.goods = goods;
    }

    public List<BusiOrderLogVo> getLogs() {
        return logs;
    }

    public void setLogs(List<BusiOrderLogVo> logs) {
        this.logs = logs;
    }

    public BusiOrderAppointVo getAppoint() {
        return appoint;
    }

    public void setAppoint(BusiOrderAppointVo appoint) {
        this.appoint = appoint;
    }

    public List<BusiOrderBidVo> getBids() {
        return bids;
    }

    public void setBids(List<BusiOrderBidVo> bids) {
        this.bids = bids;
    }
}
