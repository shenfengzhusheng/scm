package org.xfs.scm.platform.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

public class PathUtil {
	
	private static final Map<String,String> path_cache = new TreeMap<String,String>();
	
	public static String httpPath(HttpServletRequest request){
		return path_cache.computeIfAbsent("http_path", key -> 
			"http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" );
	}
	
	public static String websocketPath(HttpServletRequest request){
		return path_cache.computeIfAbsent("websocket_path", key -> 
		"ws://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" );
	}
}
