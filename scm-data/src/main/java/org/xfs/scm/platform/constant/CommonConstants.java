package org.xfs.scm.platform.constant;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class CommonConstants {
	public static final String CURRENT_USER_KEY = "CURRENT_USERKEY_CONSTANT";
	public static final Map<String,WebSocketSession> user_session=new ConcurrentSkipListMap<String,WebSocketSession>();


	public static final String ALI_PRODUCT_GATWAY="https://openapi.alipay.com/gateway.do";

	public static final String ALI_DEV_GATWAY="https://openapi.alipaydev.com/gateway.do";

	/**
	 * 生产阿里应用授权URL
	 */
	public static final String ALI_TOAUTH_URL="https://openauth.alipay.com/oauth2/appToAppAuth.htm?";

	/**
	 * 沙箱阿里应用授权URL
	 */
	public static final String ALI_DEV_TOAUTH_URL="https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?";
}
