package org.xfs.core.util.json;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if(obj == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();     
	     
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());      
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();      
        for (PropertyDescriptor property : propertyDescriptors) {      
            String key = property.getName();      
            if (key.compareToIgnoreCase("class") == 0) {     
                continue;    
            }    
            Method getter = property.getReadMethod();    
            Object value = getter!=null ? getter.invoke(obj) : null;    
            map.put(key, value);    
        }      
     
        return map;    
    }      
	public static void main(String[] args) { 
		System.out.println("2017-08-31 22:09:20.765".length());
	}

}
