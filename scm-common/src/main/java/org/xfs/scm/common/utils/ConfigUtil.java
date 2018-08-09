package org.xfs.scm.common.utils;

import java.util.ResourceBundle;

/**
 * @Title: ConfigUtil.java
 * @Package cn.xfj.util 
 * @Description:项目参数工具类
 * @author: 神风逐胜
 * @mail:xixingyingzhongdui@gmail.com
 * @Date 2013-3-6下午4:03:34
 * @version 
 */
public class ConfigUtil {

	/**
	 * 地区
	 */
	private static final ResourceBundle global = ResourceBundle.getBundle("config.properties.dev.global");
	
	/**
	 * 获得sessionInfo名字
	 * @Description:
	 * @author: 神风逐胜
	 * @mail:xixingyingzhongdui@gmail.com
	 * @Date 2014-3-6
	 * @version 
	 * @return
	 */
	public static final String getSessionInfoName(){
		return global.getString("sessionInfoName");
	}
	
	public static final String get(String key){
		return global.getString(key);
	}
	
	public static final String getTokenName(){
		return global.getString("tokenName");
	}
	
	public static final String getGlobal(String key){
		return global.getString(key);
	}
}
