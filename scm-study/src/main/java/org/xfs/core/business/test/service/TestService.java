package org.xfs.core.business.test.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.core.business.test.mapper.TestMapper;
import org.xfs.core.business.test.po.TestPo;

import com.github.pagehelper.PageHelper;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    @Transactional
    public int add(TestPo vo) {
        return this.testMapper.addTest(vo);
    }
    
    @SuppressWarnings("static-access")
	public List<TestPo>list(TestPo vo,int page,int rows){
    	PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(page, rows);
		return this.testMapper.selectTest(vo);
    }

    /**
     * @Cacheable：主要用来配置方法，能够根据方法的请求参数对其结果进行缓存。<br> 即当重复使用相同参数调用方法的时候，方法本身不会被调用执行，<br>
     *                                              即方法本身被略过了，取而代之的是方法的结果直接从缓存中找到并返回了。
     * @param keys
     * @return
     */
    @Cacheable(value = "loadData", condition = "\"uuid\".equals(#keys)")
    public synchronized String cache(String keys) {
    	TestPo vo=new TestPo();
    	vo.setId("2");
     //   System.out.println("--------------------------------->" + this.testMapper.selectTest(vo));
        String result = null;
        if (keys.equalsIgnoreCase("uuid")) {
            result = UUID.randomUUID().toString();
            System.out.println("result:" + result);
        } else {
            result = "test" + UUID.randomUUID().toString();
        }
        return result;
    }

    @Cacheable(value = "loadData", condition = "\"uuid\".equals(#keys)", key = "'keys'+#p0")
    public String modifyCacheData(String keys) {
        String result = null;
        result = UUID.randomUUID().toString();
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 只要执行了delete方法，就刷新缓存名为"loadData"下面的所有缓存。
     * 
     * @param key
     */
    @Caching(evict = {@CacheEvict(value = "loadData", allEntries = true)})
    public void deleteCache(String key) {
        System.out.println("delete cache!");
    }

    /**
     * @CachePut：主要针对方法的配置，能够根据方法的请求参数对其结果进行缓存，和@Cacheable不同的是，它每次都会触发真实方法的调用。
     * @param keys
     * @return
     */
    @CachePut(value = "loadData", condition = "\"uuid\".equals(#keys)")
    public String putCache(String keys) {
        String result = null;
        if (keys.equalsIgnoreCase("uuid")) {
            result = UUID.randomUUID().toString();
            System.out.println("putCache result:" + result);
        } else {
            result = "test" + UUID.randomUUID().toString();
        }
        return result;
    }
}
