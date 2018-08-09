package org.xfs.test.proxy;

public class ServiceImpl implements ServiceI {

	@Override
	public String getData(String key) {
		String result=null;
		if(key==null || "".equals(key)){
			result="please input key!";
		}else if("redis".equalsIgnoreCase(key)){
			result="get data from redis!";
		}else if("mq".equalsIgnoreCase(key)){
			result="get data from mq!";
		}else{
			result="get data from db!";
		}
		return result;
	}

	@Override
	public String receiveMessage(String message) {
		System.out.println("receiveMessage message is:"+message);
		return "success";
	}

}
