package core.business.index.service;

import core.platform.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 
 * @author Jeken.Liu
 *
 */
@Service
public class IndexService {
	
	

	@Resource
	private JedisCluster jc;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=BusinessException.class)
	public String uuid(String key){
		System.out.println("=============="+jc.get("key"));
		if("error".equals(key)){
			throw new BusinessException("test error");
		}else if("done".equals(key)){
			StringBuffer sql=new StringBuffer("INSERT INTO tb_test(id,name)VALUES(?,?)");
			System.out.println(sql.toString());
		}else {
			
		}
		return UUID.randomUUID().toString().replace("-", "");
		
		
	}
}
