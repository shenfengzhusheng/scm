package org.xfs.scm.business.index.web;


import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.business.pay.account.vo.PayAccountVo;
import org.xfs.scm.business.pay.ali.service.AliPayService;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.string.StringUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/")
public class IndexWeb {

    @Resource
    private AliPayService aliPayService;
    @RequestMapping(value="/")
    public Object index(){
        return JsonResponse.success("hello world!");
    }

    @RequestMapping(value = "/qrCode.do",produces = "image/jpeg;charset=UTF-8")
    public Object qrCode(PayAccountVo vo)throws Exception{
        if(vo.getAppid()==null){
            vo.setAppid("2016091600522108");
        }
        if(vo.getAppid()!=null){
            BufferedImage bufferedImage=this.aliPayService.generatorQrCode(vo.getAppid(),"1");
            String msg="调用成功";
            if(bufferedImage!=null){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage,  "JPEG",baos);
                return baos.toByteArray();
            } else {
                msg="调用失败";
            }
            System.out.println(msg);
        }
        return null;
    }

    @RequestMapping(value = "/queryOrder.do",method = RequestMethod.GET)
    public Object queryOrder(String appid,String orderNo){
        String msg="查询失败";
        if(StringUtil.isNotBlank(orderNo)){
            if(appid==null){
                appid="2016091600522108";
            }
            AlipayTradeQueryResponse response=this.aliPayService.queryTrade(appid,orderNo,null);
            if(response.isSuccess()){
                return JsonResponse.success("查询成功",response);
            }
            msg=response.getMsg()+"\r\n"+response.getSubMsg();
        }

        return JsonResponse.fail(msg);
    }
    @RequestMapping(value = "/downloadBill.do",method = RequestMethod.GET)
    public Object downloadBill(String appid,String date){
        if(appid==null){
            appid="2016091600522108";
        }
        return JsonResponse.success("查询成功！",this.aliPayService.downloadBill(appid,date));
    }
}
