package org.xfs.rpc.simple.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * Protostuff序列化与反序列化工具
 * @author xixingyingzhongdui
 *
 */
public class SerializationUtil {
	private static Map<Class<?>,Schema<?>>cachedSchmea=new ConcurrentHashMap<Class<?>,Schema<?>>();
	
	private static Objenesis objenesis=new ObjenesisStd(true);
	
	private static <T> Schema<T>getSchema(Class<T>cls){
		Schema<T>schema=(Schema<T>)cachedSchmea.get(cls);
		if(schema==null){
			schema=RuntimeSchema.createFrom(cls);
			if(schema!=null){
				cachedSchmea.put(cls, schema);
			}
		}
		return schema;
	}
	
	public static <T> byte[]serialize(T obj){
		Class<T>cls=(Class<T>)obj.getClass();
		LinkedBuffer buffer=LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try{
			Schema<T>schema=getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj,schema,buffer);
		}catch(Exception e){
			throw new IllegalStateException(e.getMessage(), e);
		}finally{
			buffer.clear();
		}
	}
	public static<T> T deserialize(byte[]data,Class<T>cls){
		try{
			T message=(T)objenesis.newInstance(cls);
			Schema<T>schema=getSchema(cls);
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		}catch(Exception e){
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	
}
