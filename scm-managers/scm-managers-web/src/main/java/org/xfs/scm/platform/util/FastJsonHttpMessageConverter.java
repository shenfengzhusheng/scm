package org.xfs.scm.platform.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
	
	private final static Charset UTF8=Charset.forName("UTF-8");
	private Charset charset=UTF8;
	private SerializerFeature[]serializerFeature;
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		InputStream input=inputMessage.getBody();
		byte[]buf=new byte[1024];
		while(true){
			int len=input.read();
			if(len==-1){
				break;
			}else{
				baos.write(buf,0,len);
			}
			
		}
		byte[]bytes=baos.toByteArray();
		if(charset==UTF8){
			return JSON.parseObject(bytes,clazz);
		}else{
			return JSON.parseObject(bytes,0,bytes.length,charset.newDecoder(),clazz);
		}
		
	}

	@Override
	protected boolean supports(Class<?> clasz) {
		
		return true;
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		OutputStream out=outputMessage.getBody();
		byte[]bytes;
		if(charset==UTF8){
			if(serializerFeature!=null){
				bytes=JSON.toJSONBytes(object, serializerFeature);
			}else{
				bytes=JSON.toJSONBytes(object, SerializerFeature.WriteDateUseDateFormat);
			}
		}else{
			String text;
			if(serializerFeature!=null){
				text=JSON.toJSONString(object, serializerFeature);
			}else{
				text=JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
			}
			bytes=text.getBytes(charset);
		}
		out.write(bytes);
	}

	public void setSupportedMediaTypes(Object arg0) {
		// TODO Auto-generated method stub
		
	}

}
