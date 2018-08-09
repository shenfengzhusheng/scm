package org.xfs.scm.platform.constant;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class CommonConstants {
	public static final String CURRENT_USER_KEY = "CURRENT_USERKEY_CONSTANT";
	public static final Map<String,WebSocketSession> user_session=new ConcurrentSkipListMap<String,WebSocketSession>();

}
