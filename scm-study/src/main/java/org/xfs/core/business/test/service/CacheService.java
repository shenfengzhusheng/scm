package org.xfs.core.business.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xfs.core.business.index.model.Person;
import org.xfs.core.platform.anntation.cache.RedisCache;
import org.xfs.core.platform.cache.service.RedisCacheService;

@Service
public class CacheService {

    @Resource
    RedisCacheService redisCacheService;

    @RedisCache(type = Person.class, expire = 1200)
    public Person getPerson(String key) {
    	System.out.println("===========not cache==============");
        Person per = new Person();
        per.setAge(30);
        per.setEmail("fengling9874@163.com");
        per.setName("刘治");
        return per;
    }
    @RedisCache(type = Person.class, expire = 1200)
    public List<Person> setPerson(String key) {
    	System.out.println("===========not cache==============");

    	List<Person>list=new ArrayList<Person>();
    	for(int i=0;i<500;i++){
    		Person per = new Person();
	        per.setAge(30);
	        per.setEmail("fengling9874@163.com");
	        per.setName("feng:"+i);
	        per.setAddr("软二22号204");
	        per.setSex(0);
	        list.add(per);
    	}
    //    this.redisCacheService.setObject("test", 1200, list);
        return list;
    }
}
