package org.xfs.netty.constant;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import org.xfs.netty.cache.CacheService;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ChannelContants {
    private static Map<Integer,SocketChannel> map=new ConcurrentHashMap<Integer, SocketChannel>();
    private static AtomicInteger clientCount=new AtomicInteger(0);
    private static String CLIENT_PREFIX="CLIENT:";
    public static void add(Integer clientId,SocketChannel socketChannel) {
        if(!map.containsKey(clientId)){
            System.out.println("当前还有["+clientCount.incrementAndGet()+"]个客户端在线！");
        }
        map.put(clientId, socketChannel);
    //    System.out.println("缓存："+CacheService.setObject(CLIENT_PREFIX+clientId,socketChannel));

    }
    public static Channel get(Integer clientId) {
        return map.get(clientId);
    }
    public static int onlineClinets(){
        return clientCount.intValue();
    }
    public static void remove(Integer clientId) {
        for(Integer key:map.keySet()){
            if(key==clientId){
                map.remove(key);
                System.out.println("客户端["+clientId+"]断开后当前还有["+ clientCount.decrementAndGet()+"]个客户端在线！");

            }
        }
   //     System.out.println("断开缓存："+CacheService.del(CLIENT_PREFIX+clientId));

    }

    public static void remove(SocketChannel socketChannel) {
        for(Map.Entry entry:map.entrySet()) {
            if(entry.getValue()==socketChannel) {
                String key=entry.getKey().toString();
                map.remove(entry.getKey());
                System.out.println("客户端["+key+"]断开后当前还有["+ clientCount.decrementAndGet()+"]个客户端在线！");

            }
        }
    }

    public static Set<Integer> clientsSet(){
        if(!map.isEmpty()){
            return  map.keySet();
        }else{
            return null;
        }
    }}
