package org.xfs.rpc.simple.service;

import java.util.List;

import org.xfs.rpc.simple.MemoryCache;
import org.xfs.rpc.simple.annotation.RpcService;
import org.xfs.rpc.simple.model.Item;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

	@Override
	public String hello(String name) {
		return "Hello " + name+ "!";
	}

	@Override
	public Item findById(Long itemId) {
		List<Item>list=MemoryCache.items;
		Item result=null;
		if(list!=null){
			for(Item item:list){
				//System.out.println(item+"result:"+(item.getItemId().longValue()==itemId.longValue()));
				if(item.getItemId().longValue()==itemId.longValue()){
					result= item;
					break;
				
				}
				
			}
		}
		return result;
	}

}
