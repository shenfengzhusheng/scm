package org.xfs.scm.data.business.pay.record.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.service.CustomerServiceI;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;
import org.xfs.scm.data.business.pay.ali.enums.AliPayStatus;
import org.xfs.scm.data.business.pay.ali.model.AliPayBackModel;
import org.xfs.scm.data.business.pay.record.mapper.PayRecordMapper;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PayRecordServiceImpl implements PayRecordServiceI {

    @Resource
    private PayRecordMapper payRecordMapper;

    @Resource
    private CustomerServiceI customerService;


    @Override
    public int remove(PayRecordVo recordVo) {
        return this.payRecordMapper.removePayRecord(recordVo);
    }

    @Override
    public int add(PayRecordVo recordVo) {
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
    public int pay(PayRecordVo recordVo) {
        return this.payRecordMapper.pay(recordVo);
    }

    @Override
    public List<PayRecordPo> find(PayRecordVo recordVo) {
        return this.buildCustomer(this.payRecordMapper.getPayRecords(recordVo));
    }

    private List<PayRecordPo> buildCustomer( List<PayRecordPo>list){
        if(!list.isEmpty()){
            Set<Long> custIds=new HashSet<>();
            for(PayRecordPo payRecordPo:list){
                custIds.add(Long.parseLong(payRecordPo.getCustId().toString()));
            }
            if(!custIds.isEmpty()){
                List<Customer>customers=this.customerService.getCustomers(new CustomerVo(custIds));
                if(!customers.isEmpty()){
                    for(PayRecordPo payRecordPo:list){
                        for (Customer customer:customers){
                            if(payRecordPo.getCustId().longValue()==customer.getCustId().longValue()){
                                payRecordPo.setCustomer(customer);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
    @Override
    public Grid<PayRecordPo> grid(PayRecordVo recordVo) {
        Page<PayRecordPo> page= PageHelper.startPage(recordVo.getPage(),recordVo.getRows());
        List<PayRecordPo>list=this.find(recordVo);
        if(!list.isEmpty()){
            Grid<PayRecordPo>grid=new Grid<>();
            grid.setRows(list);
            grid.setTotal(page.getTotal());
            return grid;
        }
        return null;
    }

    @Override
    public PayRecordPo get(PayRecordVo recordVo) {
        if(recordVo!=null){
            List<PayRecordPo>list=this.find(recordVo);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public PayRecordPo getPayRecordStatus(PayRecordVo recordVo) {

        return this.payRecordMapper.getPayRecordStatus(recordVo);
    }

    @Override
    public int modify(PayRecordVo record) {
        return this.payRecordMapper.modifyPayRecord(record);
    }
}
