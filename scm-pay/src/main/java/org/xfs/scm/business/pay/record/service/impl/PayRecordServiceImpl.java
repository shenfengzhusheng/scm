package org.xfs.scm.business.pay.record.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.xfs.scm.business.pay.ali.enums.AliPayStatus;
import org.xfs.scm.business.pay.ali.model.AliPayBackModel;
import org.xfs.scm.business.pay.record.entity.PayRecordWithBLOBs;
import org.xfs.scm.business.pay.record.mapper.PayRecordMapper;
import org.xfs.scm.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.business.pay.record.vo.PayRecordVo;


import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PayRecordServiceImpl implements PayRecordServiceI {

    @Resource
    private PayRecordMapper payRecordMapper;
    @Override
    public int removePayRecord(PayRecordVo recordVo) {
        return 0;
    }

    @Override
    public int addPayRecord(PayRecordVo recordVo) {
        return this.payRecordMapper.addPayRecord(recordVo);
    }

    @Override
    public int pay(AliPayBackModel recordVo) {
        PayRecordVo vo=new PayRecordVo();
        vo.setAppid(recordVo.getApp_id());
        vo.setOrderNo(recordVo.getOut_trade_no());
        vo.setRecordNo(recordVo.getTrade_no());
        vo.setNotifyId(recordVo.getNotify_id());
        try {
            vo.setMemo(Base64Utils.encodeToString(JSON.toJSONString(recordVo).getBytes("GBK")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        vo.setRecordType("QRPAY");
        vo.setPayTime(recordVo.getGmt_payment());
        vo.setReceiptAmount(new BigDecimal(recordVo.getReceipt_amount()));
        vo.setInvoiceAmount(new BigDecimal(recordVo.getInvoice_amount()));
        vo.setDiscountAmount(new BigDecimal(recordVo.getPoint_amount()));
        vo.setPayStatus(recordVo.getTrade_status());
        vo.setLastUpdateTime(recordVo.getNotify_time());
        vo.setPayUser(recordVo.getBuyer_logon_id());
        vo.setLastUpdateBy("支付接口");
        vo.setOldPayStatus(AliPayStatus.WAIT_BUYER_PAY.getCode());
        return this.payRecordMapper.pay(vo);
    }

    @Override
    public List<PayRecordWithBLOBs> getPayRecords(PayRecordVo recordVo) {
        return null;
    }

    @Override
    public int modifyPayRecord(PayRecordVo record) {
        return 0;
    }
}
