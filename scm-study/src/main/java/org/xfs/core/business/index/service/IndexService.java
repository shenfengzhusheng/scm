package org.xfs.core.business.index.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.core.platform.exception.BusinessException;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * @author Jeken.Liu
 *
 */
@Service
public class IndexService {
	
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private JedisCluster jc;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=BusinessException.class)
	public String uuid(String key){
		System.out.println("=============="+jc.get("key"));
		if("error".equals(key)){
			throw new BusinessException("test error");
		}else if("done".equals(key)){
			StringBuffer sql=new StringBuffer("INSERT INTO tb_test(id,name)VALUES(?,?)");
			System.out.println(this.jdbcTemplate.update(sql.toString(), new Object[]{UUID.randomUUID().toString(),UUID.randomUUID().toString().replace("-", "")}));
		}else {
			
		}
		return UUID.randomUUID().toString().replace("-", "");
		
		
	}
}
