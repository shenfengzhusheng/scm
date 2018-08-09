package org.xfs.scm.data.business.pay.wx.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xfs.api.enums.PayChannelEnum;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.exception.BusinessException;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.common.utils.http.HttpRequestUtil;
import org.xfs.scm.common.utils.http.model.HttpResponse;
import org.xfs.scm.common.utils.io.MatrixToImageWriter;
import org.xfs.scm.data.business.pay.ali.enums.AliPayStatus;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;
import org.xfs.scm.data.business.pay.wx.util.WXPayConstants;
import org.xfs.scm.data.business.pay.wx.util.WXPayUtil;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.util.MathUtil;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Service
public class WxPayService {

    private static Logger logger = LoggerFactory.getLogger(WxPayService.class);

    @Value("${config.notify_ip}")
    private String notify_ip;
    @Resource
    private CacheServiceI cacheServiceI;
    @Resource
    private PayRecordServiceI payRecordService;

    @Resource
    private PayAccountServiceI payAccountService;

    public Map<String,String> orderQuery(Map<String,String> map,String url,String key){
        return null;
    }

    public Map<String,String> createPreOrder(String appid,String device_info,int qty, String goodsName){
        try {

            PayAccountPo payAccountPo=this.payAccountService.getFormCache(appid);
            if(payAccountPo==null){
                throw new BusinessException("未知客户！");
            }
            if(qty==0){
                qty=1;
            }
            int price=new Random().nextInt(4);
            if(price==0){
                price=1;
            }
            String total_fee=price*qty+"";
            Map<String,String>map=this.createPreOrder(payAccountPo.getAppid(),payAccountPo.getPartner(),goodsName,total_fee,device_info);
            String url="https://"+WXPayConstants.DOMAIN_API+this.getUrl(payAccountPo.getIsTest());
            HttpResponse response= HttpRequestUtil.doSend(url, WXPayUtil.generateSignedXml(map,payAccountPo.getPrivateKey()), null);
            if(response.getStatus()==200){
                Map<String,String>result=WXPayUtil.xmlToMap(response.getResponse());
                if(result.get("return_code").equals(WXPayConstants.SUCCESS)){
                    System.out.println(JSON.toJSON(result));
                    String sign=WXPayUtil.generateSignature(result, payAccountPo.getPrivateKey());
                    System.out.println("sign	"+sign+"	:"+sign.equals(result.get("sign")));
                    if(sign.equals(result.get("sign"))){
                        this.savePreOrder(payAccountPo.getCustId(),payAccountPo.getAppid(),map.get("out_trade_no"),
                                 result.get("code_url"),payAccountPo.getCustomer().getCustName(),device_info,result.get("prepay_id"),total_fee,map.get("body"),qty);
                        return result;
                    }else{
                        throw new BusinessException("微信支付生成预付款签名异常！{}",result.get("sign"));
                    }
                }else{
                    throw new BusinessException("微信支付生成预付款单异常！{}",result.get("return_msg"));
                }
            }else{
                throw new BusinessException(response.getError());
            }

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }
    private void savePreOrder(Long custId,String appid,String orderNo,String qrCode,String custName,String termial,String record_no,String totalAmount,String subject,int qty){
        PayRecordVo payRecordVo=new PayRecordVo();
        payRecordVo.setPayChannel(PayChannelEnum.WX_PAY.getCode());
        payRecordVo.setCustId(custId);
        payRecordVo.setAppid(appid);
        payRecordVo.setOrderNo(orderNo);
        payRecordVo.setPayQrcode(qrCode);
        payRecordVo.setCustName(custName);
        payRecordVo.setTerminal(termial);
        payRecordVo.setCurrencyType("CNY");
        payRecordVo.setTotalAmount(MathUtil.divide(totalAmount,100,2));
        payRecordVo.setQty(new BigDecimal(qty));
        payRecordVo.setSubject(subject);
        payRecordVo.setRecordNo(record_no);
        payRecordVo.setCreatedBy("支付系统");
        payRecordVo.setCreatedTime(new Date());
        payRecordVo.setLastUpdateBy("支付系统");
        payRecordVo.setLastUpdateTime(payRecordVo.getCreatedTime());
        payRecordVo.setPayStatus(AliPayStatus.WAIT_BUYER_PAY.getCode());
        this.payRecordService.add(payRecordVo);
    }

    public BufferedImage qcCode(String appid,String device_info,int qty,String goodsName){
        Map<String,String>result=this.createPreOrder(appid,device_info,qty,goodsName);
        if(result!=null && WXPayConstants.SUCCESS.equals(result.get("return_code"))){
            return MatrixToImageWriter.writeInfoToJpgBuff( result.get("code_url"));
        }else{
            throw new BusinessException("业务异常！");

        }
    }


    private Map<String,String>createPreOrder(String appid, String mch_id,String goodsName,String total_fee,String device_info){
        String trade_type="NATIVE";
        String notify_url="http://"+notify_ip+"/pay/wx/pay_notify.html";
        Map<String,String>map=new LinkedHashMap<String,String>();
        map.put("appid", appid);
        map.put("mch_id",mch_id);
        map.put("device_info",device_info);
        map.put("nonce_str", IdGenerator.generator());
        String orderNo= new IdGenerator().nextId1();
        map.put("body",goodsName );
        System.out.println("orderNo:"+orderNo);
        map.put("out_trade_no",orderNo);
        if(total_fee.equals("0")){
            total_fee="1.0";
        }
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", notify_ip);
        map.put("notify_url", notify_url);
        map.put("trade_type", trade_type);
        return map;
    }

    private String getUrl(boolean isTest){
        if(isTest){
            return WXPayConstants.SANDBOX_UNIFIEDORDER_URL_SUFFIX;
        }
        return WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
    }

}
