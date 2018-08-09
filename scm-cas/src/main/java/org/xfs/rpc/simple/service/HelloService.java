package org.xfs.rpc.simple.service;

import org.xfs.rpc.simple.model.Item;

/**
 * 定义服务接口
 * @author xixingyingzhongdui
 *
 */
public interface HelloService {
	String hello(String name);
	
	Item findById(Long itemId);	
}
