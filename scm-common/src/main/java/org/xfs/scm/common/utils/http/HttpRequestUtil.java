package org.xfs.scm.common.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.xfs.scm.common.utils.http.model.HttpResponse;
import org.xfs.scm.common.utils.json.FastJsonUtil;

/**
 *http请求工具类 
 **/
public class HttpRequestUtil{
	private final static int CONNECTTIMEOUT=5*1000;
	private final static int READTIMEOUT=30*1000;
	private final static String CHARSET="UTF-8";
	public static HttpResponse doJsonRequest(String url, Object obj, String token){
		return doSend(url,obj,"POST","application/json",token,CHARSET);
	}
	public static HttpResponse doSend(String url,Object obj,String token){
		return doSend(url,obj,token,CHARSET);
	}
	public static HttpResponse doSend(String url,Object obj,String token,String charset){
		return doSend(url,obj,"POST",token,charset);
	}
	public static HttpResponse doSend(String url,Object obj,String method,String token,String charset){
		return doSend(url,obj,method,"html/text",token,charset);
	}
	
	
	public static HttpResponse doSend(String url,Object obj,String method,String contentType,String token,String charset){
		return doSend(url,obj,method,0,contentType,token,charset);
	}
	public static HttpResponse doSend(String url,Object obj,String method,Integer verification,String contentType,String token,String charset){
		HttpResponse response=new HttpResponse();
		StringBuffer buf=new StringBuffer();
		HttpURLConnection conn=null;
		InputStream inputStream=null;
		OutputStream out=null;
		BufferedReader in=null;
		try{

			conn=(HttpURLConnection)new URL(url).openConnection();
			//conn.setRequestProperty( "Content-Type","application/json");
			conn.setRequestProperty( "Content-Type",contentType);


			conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		    conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(CONNECTTIMEOUT);
			conn.setReadTimeout(READTIMEOUT);
			conn.setRequestProperty("Charset", CHARSET);

			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			if(verification==1 || token!=null){//需要校验
				String userName = "user";
				String password = "password";
				String input = userName+":"+password;    //用户名以及登录密码
				//oss token校验
				if(token==null){ 
		        	if(input!=null && input.trim().length()>0){
		        		token="Basic   "+Base64.getEncoder().encodeToString(input.getBytes());   
		        	}
		        	 
		        }
				conn.setRequestProperty("Authorization",  token);   //设置用户名，用户密码
			}
		
			if(obj!=null){
				Map<String,Object>requestParam=null;
				String content=null;
				if(obj instanceof String){
					content=obj.toString();
				}else{
					if(contentType.equals("application/json")){
						content=object2Json(obj);
					}else{
						if(obj instanceof Map){
							requestParam=(Map<String, Object>) obj;
						}
						requestParam=objectToMap(obj);
						content=prepareParam(requestParam,charset);
					}

				}
			//	System.out.printf("request content is【%s】",content);
				if(null == content) {
					conn.setDoOutput(false);
				} else {
					conn.setDoOutput(true);
					byte[] data = content.getBytes(CHARSET);
					conn.setRequestProperty("Content-Length", String.valueOf(data.length));
					out = conn.getOutputStream();
					out.write(data);
					out.flush();
				}
			}
			int status = conn.getResponseCode();
			response.setStatus(status);
            if (status / 100 == 2) {
            	inputStream = conn.getInputStream();
            } else {
            	inputStream = conn.getErrorStream();
            }
			InputStreamReader isr=null;
			if(charset!=null){
				isr=new InputStreamReader(inputStream,charset);
				//System.out.println("response encoding is:"+isr.getEncoding());
			}else{
				isr=new InputStreamReader(conn.getInputStream(),CHARSET);
			}
			in=new BufferedReader(isr);
			String inputLine;
			while((inputLine=in.readLine())!=null){
				buf.append(inputLine);
			}
			response.setResponse(buf.toString());
		}catch(Exception e){
			response.setStatus(100);
			response.setError(e.getMessage());
			response.setResponse(e.getMessage());
			e.printStackTrace();
		}finally{
			if(inputStream !=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null){
				try{
					in.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			if (out != null){
				try{
					out.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			if(conn!=null){
				conn.disconnect();
			}
		}
		return response;
	}
	
	/**
	 * 组装请求内容
	 * @param param
	 * @param chareset
	 * @return
	 */
	public static String prepareParam(Map<String,Object>param,String chareset){
		StringBuffer buf=new StringBuffer("");
		if(!param.isEmpty()){
			for(String key:param.keySet()){
				String value=param.get(key)!=null?param.get(key).toString():"";
				try {
					if(buf.length()>0){
						buf.append("&");
					}
					buf.append(key).append("=").append(URLEncoder.encode(value, chareset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
		}
		return buf.toString();
	}
	/**
	 * 对象to map;
	 * @param obj
	 * @return
	 */
	public static Map<String,Object>objectToMap(Object obj){
		if(obj==null)
			return null;
        Map<String, Object> map = new HashMap<String, Object>();    

	  	try {
	        Field[] declaredFields = obj.getClass().getDeclaredFields();
	        for(Field field:declaredFields){
	            field.setAccessible(true); 
	            if(field.get(obj)!=null){
		            map.put(field.getName(), field.get(obj));

	            }
	        }
	  	} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	} 
	
	/**
	 * 对象tojson
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj){
		if(obj==null){
			return "";
		}
		return JSON.toJSONString(obj);

	}
	public static void main(String[]args){
		StringBuffer buf=new StringBuffer("");
		System.out.println(buf.length());

		Map<String,Object>params=new HashMap<String,Object>();
		params.put("mobile", "18649611393");
		params.put("temp_id", 1);

		Map<String,Object>ms=new HashMap<String,Object>();
		ms.put("code", 9999);
		params.put("temp_para", ms);
		System.out.println(object2Json(params));
	}
	public static Object doGetByObject(String urlStr,Object request,Class<?> clazz) {
		String json= doGet(urlStr,request);
		if(json!=null){
			Object object= FastJsonUtil.toObject(json,clazz);
			return object;
		}
		return null;
	}
	public static String doGet(String urlStr,Object request) {
		try {
			Map<String,Object>map=objectToMap(request);
			return doGet(urlStr,map,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String doGet(String urlStr,Map<String, Object> paramMap){
		try {
			String response=doGet(urlStr,paramMap,null);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String doGet(String urlStr,Map<String, Object> paramMap,String token) throws Exception{
		String paramStr = prepareParam(paramMap);
		urlStr+="?"+paramStr;
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		if(token!=null){
			conn.setRequestProperty("Authorization", token);
		}
		conn.setDoInput(true);
		conn.setConnectTimeout(CONNECTTIMEOUT);
		conn.setReadTimeout(READTIMEOUT);
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		StringBuilder buffer=new StringBuilder();
		String line;
		while((line = reader.readLine())!=null){
			buffer.append(line);
		}
		reader.close();
		return buffer.toString();
	}
	public static String prepareParam(Map<String, Object> paramMap){
		StringBuffer sb = new StringBuffer();
		if(paramMap.isEmpty()){
			return "";
		}else{
			for (String key : paramMap.keySet()) {
				String value = paramMap.get(key)==null?"":paramMap.get(key).toString();
				try {
					if(sb.length()<1){
						sb.append(key).append("=").append(URLEncoder.encode(value,"UTF-8"));
					}else{
						sb.append("&").append(key).append("=").append(URLEncoder.encode(value,"UTF-8"));
					}
				} catch (Exception e) {

				}
			}
		}

		return sb.toString();
	}


}
