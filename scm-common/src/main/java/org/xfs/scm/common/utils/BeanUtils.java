package org.xfs.scm.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;


/**
 * @Title: BeanUtils.java
 * @Description:
 *
 * @author: 神风逐胜
 * @mail:xixingyingzhongdui@gmail.com
 * @Date 2013-3-7上午9:53:26
 * @version 
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
	public static void copyNotNullProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
		copyNotNullProperties(source, target, null, ignoreProperties);
	}

	public static void copyNotNullProperties(Object source, Object target, Class<?> editable) throws BeansException {
		copyNotNullProperties(source, target, editable, null);
	}

	public static void copyNotNullProperties(Object source, Object target) throws BeansException {
		copyNotNullProperties(source, target, null, null);
	}

	@SuppressWarnings("rawtypes")
	private static void copyNotNullProperties(Object source, Object target, Class<?> editable, String[] ignoreProperties) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						if (value != null || readMethod.getReturnType().getName().equals("java.lang.String")) {// 这里判断以下value是否为空，当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等，如果是String类型，则不需要验证是否为空
							boolean isEmpty = false;
							if (value instanceof Set) {
								Set s = (Set) value;
								if (s == null || s.isEmpty()) {
									isEmpty = true;
								}
							} else if (value instanceof Map) {
								Map m = (Map) value;
								if (m == null || m.isEmpty()) {
									isEmpty = true;
								}
							} else if (value instanceof List) {
								List l = (List) value;
								if (l == null || l.size() < 1) {
									isEmpty = true;
								}
							} else if (value instanceof Collection) {
								Collection c = (Collection) value;
								if (c == null || c.size() < 1) {
									isEmpty = true;
								}
							}
							if (!isEmpty) {
								Method writeMethod = targetPd.getWriteMethod();
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}
								writeMethod.invoke(target, value);
							}
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	} 
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String,String>object2Map(Object obj){
		if(obj==null)
			return null;
		Map<String, String> map = new HashMap<String, String>();

		try {
			Field[] declaredFields = obj.getClass().getDeclaredFields();
			for(Field field:declaredFields){
				field.setAccessible(true);
				if(field.get(obj)!=null){
					map.put(field.getName(), field.get(obj).toString());
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
