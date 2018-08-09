package org.xfs.pay.wx.test;

import com.alibaba.fastjson.JSON;
import org.xfs.pay.wx.util.WXPayConstants;
import org.xfs.pay.wx.util.WXPayUtil;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.common.utils.http.HttpRequestUtil;
import org.xfs.scm.common.utils.http.model.HttpResponse;
import org.xfs.scm.common.utils.json.FastJsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class PayTest {
	//private static String xmlStr="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx9b29944902a70e7f]]></appid><mch_id><![CDATA[1408305702]]></mch_id><device_info><![CDATA[11224]]></device_info><nonce_str><![CDATA[95Zg3sTQAQp5u8wQ]]></nonce_str><sign><![CDATA[01D7B877A616AED45BC8F3B3A7ECDDC7]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx2111004260173874d38634673058150847]]></prepay_id><trade_type><![CDATA[NATIVE]]></trade_type><code_url><![CDATA[weixin://wxpay/bizpayurl?pr=ZXHcZuN]]></code_url></xml>";
	private static String key="feng0123456789987654321012345678";
	private static String appid="wx9b29944902a70e7f";
	private static String mch_id="1408305702";
	public static void main(String[] args)throws Exception {
		//queryOrder();
		//colseOrder();
	//	System.out.println(DateUtil.parseDate("20091225091010"));
		notifyModel();
	}
	public static void notifyModel() throws Exception{
		String json="{\"nonce_str\":\"hzfVxQNpYXkwLACE\",\"device_info\":\"8857\",\"code_url\":\"weixin://wxpay/bizpayurl?pr=yUf45dW\",\"appid\":\"wx9b29944902a70e7f\",\"sign\":\"591B095D574A1D77F60180EFA162872F\",\"trade_type\":\"NATIVE\",\"return_msg\":\"OK\",\"result_code\":\"SUCCESS\",\"mch_id\":\"1408305702\",\"return_code\":\"SUCCESS\",\"prepay_id\":\"wx24090004279505bc6552335b1509260444\"}";
		Map<String,String>map=FastJsonUtil.toObject(json, HashMap.class);
		System.out.println(WXPayUtil.generateSignedXml(map, key));
		//System.out.println(FastJsonUtil.toObject(json, WxPayNotifyModel.class));
	}
	public static void callback()throws Exception{
	}
	public static void colseOrder()throws Exception{
		Map<String,String>map=new LinkedHashMap<String,String>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);	
		String orderNo="201805221005514670";// new IdGenerator().nextId1();

		//map.put("transaction_id", "220929079582760c2e9397d82035425846");
		map.put("out_trade_no", orderNo);
		map.put("sign_type", "MD5");
		map.put("nonce_str", IdGenerator.generator());
		String content=WXPayUtil.generateSignedXml(map, key);
		System.out.println("request is:"+content);
		String url="https://"+WXPayConstants.DOMAIN_API+WXPayConstants.CLOSEORDER_URL_SUFFIX;
		
		HttpResponse response=HttpRequestUtil.doJsonRequest(url, content, null);
		System.out.println(response);

		if(response.getStatus()==200) {
			Map<String,String>result=xml2Map(response.getResponse());
			System.out.println("result:"+result);
			
		}

	}
	
	
	
	/**
	 * 查询订单
	 * @project qht
	 * @throws Exception
	 * @company 企货通(福建)科技有限公司
	 * @datetime 2018年5月21日下午4:24:29
	 * @versions 1.0
	 * @author liujc
	 */
	public static void queryOrder() throws Exception {
		String transaction_id="";
		String orderNo="201805221405425890";// new IdGenerator().nextId1();

		Map<String,String>map=new LinkedHashMap<String,String>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);	
	//	map.put("transaction_id", transaction_id);
		map.put("out_trade_no", orderNo);
		map.put("sign_type", "MD5");
		map.put("nonce_str", IdGenerator.generator());
		String content=WXPayUtil.generateSignedXml(map, key);
		System.out.println("request is:"+content);
		
		String url="https://"+WXPayConstants.DOMAIN_API+WXPayConstants.ORDERQUERY_URL_SUFFIX;
		
		
		HttpResponse response=HttpRequestUtil.doJsonRequest(url, content, null);
		System.out.println(response);

		if(response.getStatus()==200) {
			Map<String,String>result=xml2Map(response.getResponse());
			System.out.println("result:"+result);
			
		}
		
		
		
	}
	
	public static void preOrder() {

		String orderUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";
		String goodsName="12L测试商品";
		String device_info="11224";
		String nonce_str="555652757147493e8827838a2d13cfd";//IdGenerator.generator();
	//	System.out.println("lenth:"+nonce_str.length());
		String orderNo="201805211005547260";// new IdGenerator().nextId1();
		String spbill_create_ip="121.42.205.204";
		String notify_url="http://"+spbill_create_ip+"/pay/wx/pay_notify.html";
		
	//	String key="9dc5b1d5448497e791b3f74b16d854ad";//公众号的
	//	String key="feng0123456789987654321012345678";
		String trade_type="NATIVE";
		String total_fee=new Random().nextInt(10)+"";
		
		Map<String,String>map=new LinkedHashMap<String,String>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("device_info",device_info);
		map.put("nonce_str", nonce_str);
		map.put("body", goodsName);
		map.put("out_trade_no",orderNo);
		map.put("total_fee", total_fee);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("notify_url", notify_url);
		map.put("trade_type", trade_type);
		try {
			String content=WXPayUtil.generateSignedXml(map,key);
			System.out.println("content:"+content);
			HttpResponse response=HttpRequestUtil.doSend(orderUrl, content, null);
			
			System.out.println(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public static Map<String,String> xml2Map(String xml){
		try {
			Map<String,String>result=WXPayUtil.xmlToMap(xml);
			if(WXPayConstants.SUCCESS.equals(result.get("return_code"))) {
				System.out.println(JSON.toJSON(result));
				String sign=WXPayUtil.generateSignature(result, key);
				System.out.println("sign	"+sign+"	:"+sign.equals(result.get("sign")));
				
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
