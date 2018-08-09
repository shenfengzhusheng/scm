package org.xfs.scm.data.business.pay.ali.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.stereotype.Service;
import org.xfs.api.enums.PayChannelEnum;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.common.utils.io.MatrixToImageWriter;
import org.xfs.scm.data.business.pay.ali.enums.AliPayStatus;
import org.xfs.scm.data.business.pay.ali.model.AliPrecreateRequest;
import org.xfs.scm.data.business.pay.ali.model.TradeOrder;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;
import org.xfs.scm.data.system.payaccount.entity.PayAccount;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.platform.config.exception.BusinessException;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AliPayService {
    @Resource
    private PayAccountServiceI payAccountService;

    @Resource
    private PayRecordServiceI payRecordService;

    public AlipayTradePrecreateResponse createPreperOrder(String appid,String terminal_id){

        PayAccountPo data=this.payAccountService.getFormCache(appid);
        if(data!=null){
            String gateway=gateWay(data.getIsTest());
            AlipayClient alipayClient = this.getAlipayClient(gateway,data);
            if(!data.getIsTest()){

            }
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.setNotifyUrl(data.getNotifyUrl());

           // String content= FastJsonUtil.toJsonString(buildPereateOrder(data));
            TradeOrder order=buildTradeOrder(data);
            String content=JSON.toJSONString(order);
            request.setBizContent(content);//设置业务参数
            AlipayTradePrecreateResponse response = null;
            try {
                response = alipayClient.execute(request);
                if(response.isSuccess()){
                    this.saveOrder(data,order,response.getQrCode());;
//                   final String qrCode=response.getQrCode();
//                   new Thread(new Runnable() {
//                       @Override
//                       public void run() {
//
//                       }
//                   }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }
        return null;
    }
    private void saveOrder(PayAccountPo data,TradeOrder order,String qrCode){
        PayRecordVo vo=new PayRecordVo();
        vo.setAppid(data.getAppid());
        vo.setPayChannel(PayChannelEnum.ALI_PAY.getCode());
        vo.setCreatedBy("支付系统");
        vo.setCreatedTime(new Date());
        vo.setLastUpdateBy("支付系统");
        if(data.getCustomer()!=null){
            vo.setCustName(data.getCustomer().getCustName());
        }
        vo.setQty(new BigDecimal(new Random().nextInt(10)));
        vo.setLastUpdateTime(vo.getCreatedTime());
        vo.setOrderNo(order.getOut_trade_no());
        vo.setTotalAmount(new BigDecimal(order.getTotal_amount()));
        vo.setCurrencyType("CNY");
        vo.setCustId(data.getCustId());
        vo.setPayStatus(AliPayStatus.WAIT_BUYER_PAY.getCode());
        vo.setSubject(order.getSubject());
        vo.setTerminal(order.getTerminal_id());
        vo.setPayQrcode(qrCode);
        this.payRecordService.add(vo);
    }
    public BufferedImage generatorQrCode(String appid,String terminal_id){
        AlipayTradePrecreateResponse response=this.createPreperOrder(appid,terminal_id);
        if(response.isSuccess()){
            return MatrixToImageWriter.writeInfoToJpgBuff( response.getQrCode());

        }else{
            throw new BusinessException("调用淘宝api异常！");
        }

    }

    private TradeOrder buildTradeOrder(PayAccount data){
        TradeOrder order=new TradeOrder();
       // order.set
        order.setOut_trade_no("qht"+new IdGenerator().nextId1());
        if(data.getIsTest()){
            order.setTotal_amount(new Random().nextInt(12000)*0.01);
            order.setSubject("测试商品订单");
        }else{
            order.setTotal_amount(0.01);
            order.setSubject("线上商品订单");
        }
        order.setTerminal_id("qht-000");
        order.setStore_id("terminal-01");
        order.setTimeout_express("15m");

        return order;
    }
    private AliPrecreateRequest buildPereateOrder(PayAccount data){
        AliPrecreateRequest order=new AliPrecreateRequest();
        order.setOut_trade_no("qht"+new IdGenerator().nextId1());
        if(data.getIsTest()){
            order.setTotal_amount(12.25);
            order.setSubject("测试商品订单");
        }else{
            order.setTotal_amount(0.01);
            order.setSubject("线上商品订单");
        }

        order.setStore_id("terminal-01");
        order.setTimeout_express("15m");

        return order;
    }

    /**
     *
     * @param out_trade_no 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
     * @param trade_no 支付宝交易号，和商户订单号不能同时为空
     * @return
     */
    public AlipayTradeQueryResponse queryTrade(String appid,String out_trade_no,String trade_no){
        PayAccountPo data=this.payAccountService.getFormCache(appid);
        if(data!=null){
            String gateway=gateWay(data.getIsTest());
            AlipayClient alipayClient = this.getAlipayClient(gateway,data);
            if(alipayClient!=null){
                AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
                Map<String,String> queryOrder=new HashMap<>();
                queryOrder.put("out_trade_no",out_trade_no);
              //  queryOrder.put("trade_no",out_trade_no);
                String content= JSON.toJSONString(queryOrder);
                System.out.println("content:"+content);
                request.setBizContent(content);
                AlipayTradeQueryResponse response = null;
                try {
                    response = alipayClient.execute(request);
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
                return response;
            }
        }

        return null;
    }

    public AlipayDataDataserviceBillDownloadurlQueryResponse downloadBill(String appid, String date){
        PayAccountPo data=this.payAccountService.getFormCache(appid);
        if(data!=null) {
            String gateway = gateWay(data.getIsTest());
            AlipayClient alipayClient = this.getAlipayClient(gateway, data);
            if(alipayClient!=null){
                Map<String,String>param=new HashMap<>();
                param.put("bill_type","trade");
                param.put("bill_date",date);
                AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();//创建API对应的request类
                request.setBizContent(JSON.toJSONString(param));
                try {
                    return  alipayClient.execute(request);//通过alipayClient调用API，获得对应的response类
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
//
//    public PayAccount getPayAccount(String appid){
//        if(StringUtil.isNotEmpty(appid)){
//            return this.payAccountService.getByAppId(appid);
//        }
//        return null;
//    }
    /**
     * 获取网关
     * @param isTest
     * @return
     */
    private String gateWay(boolean isTest){
        if(isTest){
            return "https://openapi.alipaydev.com/gateway.do";
        }else{
            return "https://openapi.alipay.com/gateway.do";
        }
    }

    private AlipayClient getAlipayClient(String gateway, PayAccount data){
        if(data!=null){
           return new DefaultAlipayClient(gateway,data.getAppid(),data.getPrivateKey(),"json","UTF-8",data.getPublicKey(),"RSA2");
        }
        return null;
    }
}
