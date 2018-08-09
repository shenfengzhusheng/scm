package org.xfs.scm.platform.config.mq.active.listener;

import org.apache.activemq.command.ActiveMQDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.api.enums.MessageTypeEnums;
import org.xfs.api.enums.PayChannelEnum;
import org.xfs.api.model.WebMessage;
import org.xfs.scm.common.utils.date.DateUtil;
import org.xfs.scm.data.business.pay.ali.enums.AliPayStatus;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;
import org.xfs.scm.data.business.pay.wx.modal.WxPayNotifyModel;
import org.xfs.scm.data.business.pay.wx.util.WXPayConstants;
import org.xfs.scm.data.business.pay.wx.util.WXPayUtil;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.config.websocket.SystemWebSocketHandler;
import org.xfs.scm.platform.config.websocket.model.MessageSocketModel;
import org.xfs.scm.platform.util.MathUtil;
import org.xfs.scm.platform.util.json.FastJsonUtil;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.math.BigDecimal;
import java.util.Map;

public class ConsumerMessageListener implements MessageListener {

    private static final Logger logger= LoggerFactory.getLogger(ConsumerMessageListener.class);

    private PayRecordServiceI payRecordService;
    private PayAccountServiceI payAccountService;
    private CacheServiceI cacheService;

    public ConsumerMessageListener(){}
    public ConsumerMessageListener(CacheServiceI cacheService, PayRecordServiceI payRecordService, PayAccountServiceI payAccountService){
        this.cacheService=cacheService;
        this.payAccountService=payAccountService;
        this.payRecordService=payRecordService;
    }

    @Override
    public void onMessage(Message message) {
      try{
          String queueName=((ActiveMQDestination)message.getJMSDestination()).getPhysicalName();

          if(message instanceof TextMessage){
              final String ms = ((TextMessage) message).getText();

              System.out.println("收到消息：" + ms);
              if(queueName.equals("netty")){
                  //转换成相应的对象
                  WebMessage request= FastJsonUtil.toObject(ms,WebMessage.class);
                  if (request == null) {
                      return;
                  }
                  MessageSocketModel messageSocketModel=new MessageSocketModel();
                  messageSocketModel.setType(request.getType());
                  messageSocketModel.setName("设备"+request.getClientId());
                  messageSocketModel.setMessage(MessageTypeEnums.getNameByValue(request.getType()));
                  messageSocketModel.setSendTime(request.getReceiveTime());
                  messageSocketModel.setId(request.getRequestId());
                  SystemWebSocketHandler.send2Users(messageSocketModel);
              }  if(queueName.equals("preOrder")){
                  Map<String,String> map= WXPayUtil.xmlToMap(ms);
                  WxPayNotifyModel payNotifyModel=FastJsonUtil.toObject(FastJsonUtil.toJsonString(map), WxPayNotifyModel.class);
                  if(payNotifyModel!=null){
                      //签名验证
                      if(WXPayConstants.SUCCESS.equals(payNotifyModel.getResult_code())){
                          String sign=payNotifyModel.getSign();
                          PayAccountPo payAccountPo=this.payAccountService.getFormCache(payNotifyModel.getAppid());
                          if(payAccountPo!=null){
                              String realSign=WXPayUtil.generateSignature(map,payAccountPo.getPrivateKey());
                              if(sign.equals(realSign)){
                                  //业务处理
                                  this.dealPay(payNotifyModel,ms);
                              }
                          }

                      }
                  }
              }else{

              }
          }else if(message instanceof MapMessage){

          }else{

          }
      }catch (Exception e){
          logger.error("消息监听异常！",e);
      }
    }
    private void dealPay(WxPayNotifyModel payNotifyModel,String msg){
        //1、查询订单是否有效，无效直接跳出，有效继续
        PayRecordVo vo=new PayRecordVo();
        vo.setAppid(payNotifyModel.getAppid());
        if(payNotifyModel.getOut_trade_no()!=null){
            vo.setOrderNo(payNotifyModel.getOut_trade_no());
        }else{
            vo.setRecordNo(payNotifyModel.getPrepay_id());
        }
        vo.setPayChannel(PayChannelEnum.WX_PAY.getCode());

        PayRecordPo po=this.payRecordService.getPayRecordStatus(vo);
        if(po!=null){//预付款单存在并且未付款
            //2、变更付款凭证记录
            vo.setRecordNo(payNotifyModel.getTransaction_id());
            vo.setRecordType("QRPAY");
            vo.setMemo(msg);
            int receiptAmount=0;
            if(payNotifyModel.getCash_fee()!=null){
                receiptAmount+=payNotifyModel.getCash_fee();
            }
            if(receiptAmount==0){
                receiptAmount+=payNotifyModel.getTotal_fee();
            }
            if(payNotifyModel.getCoupon_fee()!=null){
                vo.setDiscountAmount(MathUtil.divide(payNotifyModel.getCoupon_fee(),100,2));
                receiptAmount-=payNotifyModel.getCoupon_fee();
            }
            vo.setReceiptAmount(MathUtil.divide(receiptAmount,100,2));
            BigDecimal totalAmount=po.getReceiptAmount().add(MathUtil.divide(payNotifyModel.getTotal_fee(),100,2));
            if(totalAmount.longValue()==po.getTotalAmount().longValue()){
                vo.setPayStatus(AliPayStatus.TRADE_SUCCESS.getCode());
            }else{
                vo.setPayStatus(AliPayStatus.TRADE_PARY.getCode());
            }
            if(payNotifyModel.getOut_trade_no()==null){
                vo.setRecordNo(payNotifyModel.getPrepay_id());
            }
            vo.setNotifyId(payNotifyModel.getNonce_str());
            vo.setRecordNo(payNotifyModel.getTransaction_id());
            vo.setPayTime(DateUtil.parseDateWx(payNotifyModel.getTime_end()));
            vo.setLastUpdateTime(vo.getPayTime());
            vo.setLastUpdateBy("微信支付接口");
            vo.setOldPayStatus(po.getPayStatus());
            if(this.payRecordService.pay(vo)!=1){
                logger.error("付款异常数据库更新异常！{}",msg);
            }

        }

    }
}
