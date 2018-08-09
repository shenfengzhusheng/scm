package org.xfs.scm.data.business.pay.ali.web;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;


import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.common.utils.date.DateUtil;
import org.xfs.scm.common.utils.io.MatrixToImageWriter;
import org.xfs.scm.common.utils.log.RequestInfoUtil;
import org.xfs.scm.data.business.pay.ali.model.*;
import org.xfs.scm.data.business.pay.ali.service.AliPayService;
import org.xfs.scm.data.business.pay.record.entity.PayRecord;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.system.payaccount.entity.PayAccount;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.data.system.payaccount.vo.PayAccountVo;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;
import org.xfs.scm.platform.constant.CommonConstants;
import org.xfs.scm.platform.util.json.FastJsonUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/pay/ali/")
public class AliPayWeb extends BaseWeb {
    private String APP_PREFIX="APPID:";
    @Resource
    private PayAccountServiceI payAccountService;

    @Resource
    private CacheServiceI cacheService;

    @Resource
    private AliPayService aliPayService;

    @Resource
    private PayRecordServiceI payRecordService;
    //1、应用授权
    public Object toAuth(){
        return null;
    }

    //2、阿里授权回调
    @RequestMapping(value = "/authCallback.do",method = RequestMethod.GET)
    public Object authCallback(AliAuthCode data){
        ModelAndView modelAndView=new ModelAndView();
        String msg="获取app_auth_code失败！";
        if(data!=null){
           if(this.cacheService.set(APP_PREFIX+data.getApp_id(),data.getApp_auth_code(),86400)){
               return JsonResponse.success("获取app_auth_code成功！",data.getApp_auth_code());
           }else{
               msg="缓存失败！";
           }

        }else{
            msg="无回调信息！";
        }
        modelAndView.setViewName("aliAuth");
        modelAndView.addObject("appid",data.getApp_id());
        return modelAndView;
    }

    //2、阿里授权回调
    @RequestMapping(value = "/auth.html",method = RequestMethod.GET)
    public Object auth(AliAuthCode vo){
        ModelAndView modelAndView=new ModelAndView();
        JsonResponse<String >jsonResponse=null;
        String msg=null;
        if(vo!=null){
            PayAccount data=this.payAccountService.getByAppId(vo.getApp_id());
            AliAuthTokenRequest requestContent=new AliAuthTokenRequest(vo.getApp_auth_code());
            String url= CommonConstants.ALI_PRODUCT_GATWAY;
            if(data.getIsTest()){
                url=CommonConstants.ALI_DEV_GATWAY;
            }
            AlipayClient alipayClient = new DefaultAlipayClient(url, data.getAppid(), data.getPrivateKey(), "json", "utf-8", data.getPublicKey(), "RSA2");
            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            request.setBizContent(FastJsonUtil.toJsonString(requestContent));
            try {
                AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
                if(response.isSuccess()){
                    System.out.println("appauthtoken:"+response.getAppAuthToken());

                    System.out.println(response.getBody().toString());
                    PayAccountVo payAccountVo=new PayAccountVo();
                    payAccountVo.setPayId(data.getPayId());
                    payAccountVo.setAuthToken(response.getAppAuthToken());
                    payAccountVo.setState(true);
                    UserPo user= TokenManager.getCurrentUser();
                    payAccountVo.setLastUpdateBy(user.getLastUpdateBy());
                    payAccountVo.setLastUpdateTime(new Date());
                    if(this.payAccountService.modify(payAccountVo)==1){
                        msg="授权成功！";
                    }else{
                        msg="授权系统异常！";
                    }

                }
            } catch (AlipayApiException e) {
                msg=e.getMessage();

                e.printStackTrace();
            }

        }else{
            msg="无回调信息！";
        }

    //    return JsonResponse.fail(msg);
        modelAndView.setViewName("authStatus");
        modelAndView.addObject("msg",msg);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("aliAuthToken.do")
    public Object aliAuthToken(PayAccountVo vo){
        String msg="获取authToken失败！";
        vo.setPayType("aliPay");
        PayAccount data=this.payAccountService.getByAppId(vo.getAppid());
        String auth_code=this.cacheService.get(APP_PREFIX+data.getAppid());

        if(StringUtil.isNotEmpty(auth_code)){
            AliAuthTokenRequest requestContent=new AliAuthTokenRequest(auth_code);
            String url= CommonConstants.ALI_PRODUCT_GATWAY;
            if(data.getIsTest()){
                url=CommonConstants.ALI_DEV_GATWAY;
            }
            AlipayClient alipayClient = new DefaultAlipayClient(url, data.getAppid(), data.getPrivateKey(), "json", "utf-8", data.getPublicKey(), "RSA2");
            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            request.setBizContent(FastJsonUtil.toJsonString(requestContent));
            try {
                AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
                if(response.isSuccess()){
                    System.out.println("appauthtoken:"+response.getAppAuthToken());

                    System.out.println(response.getBody().toString());
                    PayAccountVo payAccountVo=new PayAccountVo();
                    payAccountVo.setPayId(data.getPayId());
                    payAccountVo.setAuthToken(response.getAppAuthToken());
                    payAccountVo.setState(true);
                    UserPo user= TokenManager.getCurrentUser();
                    payAccountVo.setLastUpdateBy(user.getLastUpdateBy());
                    payAccountVo.setLastUpdateTime(new Date());
                    if(this.payAccountService.modify(payAccountVo)==1){
                        return JsonResponse.success("appAuthToeken获取成功！");
                    }else{
                        msg="存储appAuthtoken失败";
                    }

                }
            } catch (AlipayApiException e) {
                msg=e.getMessage();

                e.printStackTrace();
            }
        }else{
            msg="没有auth_code!";
        }
        return JsonResponse.fail(msg);
    }
    @ResponseBody
    @RequestMapping(value = "/pay_notify.html")
    public Object payCallback(AliPayBackModel model,HttpServletRequest request){
      //  String buffer= RequestInfoUtil.getRequestInfo(request);
      //  this.cacheService.set("payMessage:"+ IdGenerator.generator(), JSON.toJSONString(model));
       if(this.validateSign(request)){
           this.payRecordService.pay(model);
           return "success";
       }
        return "fail";
    }
    private Map<String, Object> getParameter2Map (Map<String, String[]> parameterMap, InputStream is) {

        Map<String, Object> params = new TreeMap<String,Object>();
        for (Iterator iter = parameterMap.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = parameterMap.get(name);
            String valueStr = "";
            for (int i = 0,len =  values.length; i < len; i++) {
                valueStr += (i == len - 1) ?  values[i] : values[i] + ",";
            }
            if (!valueStr.matches("\\w+")){
                try {
                    if(valueStr.equals(new String(valueStr.getBytes("iso8859-1"), "iso8859-1"))){
                        valueStr=new String(valueStr.getBytes("iso8859-1"),"utf-8");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            params.put(name, valueStr);
        }
        return params;
    }
    private boolean validateSign(HttpServletRequest request){
        //获取支付方返回的对应参数
       try{
           Map<String, Object> params =getParameter2Map(request.getParameterMap(),request.getInputStream());
           if (params.get("sign") == null) {
               System.out.println("支付宝支付异常：params：" + params);
               return false;
           }
           System.out.println("支付宝支付异常：params：" + params);

       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/createOrder.do")
    public Object createOrder(PayAccountVo vo){
        if(vo.getAppid()!=null){
            AlipayTradePrecreateResponse response =this.aliPayService.createPreperOrder(vo.getAppid(),"1");
            String msg="调用成功";
            System.out.println(response.getBody());

            if(response.isSuccess()){

                System.out.println("调用成功");
            } else {
                msg="调用失败";
                System.out.println("调用失败");
            }
            return JsonResponse.success(msg,response);
        }
        return JsonResponse.fail("订单创建失败！");
    }



    private TradeOrder buildOrder(PayAccount data){
        TradeOrder order=new TradeOrder();
        order.setOut_trade_no("qht"+new IdGenerator().nextId1());
        order.setTotal_amount(1.35);
   //     order.setSeller_id(data.getSeller());
        order.setStore_id("ddd");
        order.setTerminal_id("558");
      //  order.setTimeout_express("90m");
        order.setSubject("测试商品订单");

        return order;
    }
//
//    @PostConstruct
//    public void init() {
//        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
//        aliPayConfigStorage.setPid("2088102175839213");
//        aliPayConfigStorage.setAppId("2016091600522108");
//        aliPayConfigStorage.setKeyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArHWMjC9p4ZIB69BcCwk9v2vKQBZWDkqoR0wjDAQ5mMKEeE4urrDbElHo6Wqxbq/XgxdB690/DKwVDHWyL7vD6SSfdwonabKN7eOh6fHzSEzvcbbPUZEHRBiH0TJbTi5RXNgCHR+UYe19WBdUmjmH1FPUFKwBvHAJQssZfVUK4/WU4s1/hGjrSBDGhvagXa+tc6Wc8t4kvuqELD6ZzgatpZCKaz/0pOUgPpskOXLcRxsQbf11JWRnCXtT4WYAzrbmMWBhH5uOzjW2QIWBhw6FYGDtSpSAz1UNaXCNVMa6YVLCZg+1AUaTMla7YrOoiKtzHwIxsFlsCEUoboEQ6gvj1QIDAQAB");
//        aliPayConfigStorage.setKeyPrivate("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
//        aliPayConfigStorage.setNotifyUrl("http://121.42.205.204/payBack.json");
//        aliPayConfigStorage.setReturnUrl("http://121.42.205.204/payBack.html");
//        aliPayConfigStorage.setSignType(SignUtils.RSA.name());
//        aliPayConfigStorage.setSeller("2088102175839213");
//        aliPayConfigStorage.setInputCharset("utf-8");
//        //是否为测试账号，沙箱环境
//        aliPayConfigStorage.setTest(true);
//
//        service = new AliPayService(aliPayConfigStorage);
//
//
//    }
//
//
//
//    /**
//     * 跳到支付页面
//     * 针对实时支付,即时付款
//     *
//     * @param price       金额
//     * @return 跳到支付页面
//     */
//    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
//    public String toPay( BigDecimal price) {
//        //及时收款
//        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.DIRECT);
//        //WAP
////        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.WAP);
//
//        Map orderInfo = service.orderInfo(order);
//        return service.buildRequest(orderInfo, MethodType.POST);
//    }
//
//
//
//
//    /**
//     * 获取支付预订单信息
//     *
//     * @return 支付预订单信息
//     */
//    @RequestMapping("app")
//    public Map<String, Object> app() {
//        Map<String, Object> data = new HashMap<>();
//        data.put("code", 0);
//        PayOrder order = new PayOrder("订单title", "摘要", new BigDecimal(0.01), UUID.randomUUID().toString().replace("-", ""));
//        //App支付
//        order.setTransactionType(AliTransactionType.APP);
//        data.put("orderInfo", UriVariables.getMapToParameters(service.orderInfo(order)));
//        return data;
//    }
//
//    /**
//     * 获取二维码图像
//     * 二维码支付
//     * @param price       金额
//     * @return 二维码图像
//     */
//    @RequestMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
//    public byte[] toWxQrPay( BigDecimal price) throws IOException {
//        //获取对应的支付账户操作工具（可根据账户id）
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(service.genQrPay( new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, System.currentTimeMillis()+"", AliTransactionType.SWEEPPAY)), "JPEG", baos);
//        return baos.toByteArray();
//    }
//
//
//    /**
//     * 刷卡付,pos主动扫码付款(条码付)
//     * @param authCode        授权码，条码等
//     * @param price       金额
//     * @return 支付结果
//     */
//    @RequestMapping(value = "microPay")
//    public Map<String, Object> microPay(BigDecimal price, String authCode) throws IOException {
//        //获取对应的支付账户操作工具（可根据账户id）
//        //条码付
//        PayOrder order = new PayOrder("huodull order", "huodull order", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.BAR_CODE);
//        //声波付
////        PayOrder order = new PayOrder("huodull order", "huodull order", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.WAVE_CODE);
//        //设置授权码，条码等
//        order.setAuthCode(authCode);
//        //支付结果
//        Map<String, Object> params = service.microPay(order);
//        //校验
//        if (service.verify(params)) {
//
//            //支付校验通过后的处理
//            //......业务逻辑处理块........
//
//
//        }
//        //这里开发者自行处理
//        return params;
//    }
//
//    /**
//     * 支付回调地址
//     *
//     * @param request
//     *
//     * @return
//     */
//    @RequestMapping(value = "payBack.json")
//    public String payBack(HttpServletRequest request) throws IOException {
//
//        //获取支付方返回的对应参数
//        Map<String, Object> params = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
//        if (null == params) {
//            return service.getPayOutMessage("fail", "失败").toMessage();
//        }
//
//        //校验
//        if (service.verify(params)) {
//            //这里处理业务逻辑
//            //......业务逻辑处理块........
//            return service.getPayOutMessage("success", "成功").toMessage();
//        }
//
//        return service.getPayOutMessage("fail", "失败").toMessage();
//    }
//
//
//    /**
//     * 查询
//     *
//     * @param order 订单的请求体
//     * @return 返回查询回来的结果集，支付方原值返回
//     */
//    @RequestMapping("query")
//    public Map<String, Object> query(QueryOrder order) {
//        return service.query(order.getTradeNo(), order.getOutTradeNo());
//    }
//
//
//    /**
//     * 交易关闭接口
//     *
//     * @param order 订单的请求体
//     * @return 返回支付方交易关闭后的结果
//     */
//    @RequestMapping("close")
//    public Map<String, Object> close(QueryOrder order) {
//        return service.close(order.getTradeNo(), order.getOutTradeNo());
//    }
//
//    /**
//     * 申请退款接口
//     *
//     * @param order 订单的请求体
//     * @return 返回支付方申请退款后的结果
//     */
//    @RequestMapping("refund")
//    public Map<String, Object> refund(RefundOrder order) {
//        return service.refund(order);
//    }
//
//    /**
//     * 查询退款
//     *
//     * @param order 订单的请求体
//     * @return 返回支付方查询退款后的结果
//     */
//    @RequestMapping("refundquery")
//    public Map<String, Object> refundquery(QueryOrder order) {
//        return service.refundquery(order.getTradeNo(), order.getOutTradeNo());
//    }
//
//    /**
//     * 下载对账单
//     *
//     * @param order 订单的请求体
//     * @return 返回支付方下载对账单的结果
//     */
//    @RequestMapping("downloadbill")
//    public Object downloadbill(QueryOrder order) {
//        return service.downloadbill(order.getBillDate(), order.getBillType());
//    }
//
//
//    /**
//     * 通用查询接口，根据 AliTransactionType 类型进行实现,此接口不包括退款
//     *
//     * @param order 订单的请求体
//     * @return 返回支付方对应接口的结果
//     */
//    @RequestMapping("secondaryInterface")
//    public Map<String, Object> secondaryInterface(QueryOrder order) {
//        TransactionType type = AliTransactionType.valueOf(order.getTransactionType());
//        return service.secondaryInterface(order.getTradeNoOrBillDate(), order.getOutTradeNoBillType(), type);
//    }
//
//    /**
//     * 转账
//     *
//     * @param order 转账订单
//     *
//     * @return 对应的转账结果
//     */
//    @RequestMapping("transfer")
//    public Map<String, Object> transfer(TransferOrder order) {
////        order.setOutNo("转账单号");
////        order.setPayeeAccount("收款方账户,支付宝登录号，支持邮箱和手机号格式");
////        order.setAmount(new BigDecimal(10));
////        order.setPayerName("付款方姓名, 非必填");
////        order.setPayeeName("收款方真实姓名, 非必填");
////        order.setRemark("转账备注, 非必填");
//        return service.transfer(order);
//    }
//
//    /**
//     * 转账查询
//     *
//     * @param outNo   商户转账订单号
//     * @param tradeNo 支付平台转账订单号
//     *
//     * @return 对应的转账订单
//     */
//    @RequestMapping("transferQuery")
//    public Map<String, Object> transferQuery(String outNo, String tradeNo) {
//        return service.transferQuery(outNo, tradeNo);
//    }


}
