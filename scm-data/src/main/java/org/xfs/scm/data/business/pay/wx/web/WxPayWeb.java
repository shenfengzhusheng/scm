package org.xfs.scm.data.business.pay.wx.web;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.data.business.pay.wx.service.WxPayService;
import org.xfs.scm.data.business.pay.wx.util.WXPayUtil;
import org.xfs.scm.data.system.payaccount.vo.PayAccountVo;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.util.string.StringUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/pay/wx/")
public class WxPayWeb {
    private static Logger logger = LoggerFactory.getLogger(WxPayWeb.class);
    @Resource
    private WxPayService wxPayService;

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource
    private CacheServiceI cacheService;


    @RequestMapping(value = "preOrder.do",produces = "image/jpeg;charset=UTF-8")
    public Object prePayOrder(PayAccountVo vo)throws Exception{
        if( vo.getAppid()==null|| vo.getAppid()==""){
            vo.setAppid("wx9b29944902a70e7f");
        }
        String goodsName="微信测试商品";
        int qty=new Random().nextInt(5);
        BufferedImage bufferedImage=this.wxPayService.qcCode(vo.getAppid(),"8857",qty,goodsName);
        if(bufferedImage!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,  "JPEG",baos);
            return baos.toByteArray();
        }
        return null;
    }

    @RequestMapping(value = "pay_notify.html")
    public Object pay_notify(HttpServletRequest request){
        String response= "<xml>\n" +
                "  <return_code><![CDATA[FAIL]]></return_code>\n" +
                "</xml>";
        String result =null;
        try{

            this.cacheService.incre("wxnotify");
           InputStream inStream = request.getInputStream();
           int _buffer_size = 1024;
           if (inStream != null) {

               ByteArrayOutputStream outStream = new ByteArrayOutputStream();
               byte[] tempBytes = new byte[_buffer_size];
               int count = -1;
               while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                   outStream.write(tempBytes, 0, count);
               }
               tempBytes = null;
               outStream.flush();
               //将流转换成字符串
               result = new String(outStream.toByteArray(), "UTF-8");
               if(this.validate(result)){
                   this.receiveNotify("preOrder",result);
                   response= "<xml>\n" +
                           "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                           "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                           "</xml>";
               }else{
                   this.receiveNotify("bakQueue",result);
               }


           }
       }catch (Exception e){
           this.cacheService.set("WXNOTIFY:"+ IdGenerator.generator(),result);
           logger.error("接收异常",e);
       }

       return response;
    }
    private boolean validate(String xml){
        boolean flag=false;
        if(StringUtil.isNotBlank(xml)){
            try {
                Map<String,String>map=WXPayUtil.xmlToMap(xml);
                if(map.get("appid")!=null && map.get("out_trade_no")!=null && map.get("transaction_id")!=null && map.get("total_fee")!=null ){
                    flag=true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private void receiveNotify(String queue, String content){
        this.jmsTemplate.send(queue,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(content);
            }
        });
    }



}
