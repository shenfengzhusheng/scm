package org.xfs.platform.net.netty.demo.model;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
/**
 * Server端：主要包含对SocketChannel引用的Map,ChannelHandler的实现和Bootstrap.
 * @author fengling9874
 *
 */
public class NettyChannelMap {
    private static Map<String,SocketChannel> map=new ConcurrentHashMap<String, SocketChannel>();
    public static void add(String clientId,SocketChannel socketChannel) {
    	map.put(clientId, socketChannel);
    }
    public static Channel get(String clientId) {
    	return map.get(clientId);
    }
    public static void remove(SocketChannel socketChannel) {
    	for(Map.Entry entry:map.entrySet()) {
    		if(entry.getValue()==socketChannel) {
    			map.remove(entry.getKey());
    		}
    	}
    }

    public static Set<String> clientsSet(){
        if(!map.isEmpty()){
            return  map.keySet();
        }else{
            return null;
        }
    }
}
