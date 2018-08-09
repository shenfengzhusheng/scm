package org.xfs.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyInvoke {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(final Class<?> interfaceClass,final Class<?> implementClass)throws Exception{
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				String result=method.invoke(implementClass.newInstance(), args).toString();
				return "proxy invoke :"+result;
			}});
	}
}
