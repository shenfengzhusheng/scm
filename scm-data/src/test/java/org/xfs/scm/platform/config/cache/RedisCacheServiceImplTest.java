package org.xfs.scm.platform.config.cache;

import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.goods.item.vo.ItemVo;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 神风逐胜 on 2018/2/1 0001.22:01
 * version:1.0
 */
public class RedisCacheServiceImplTest extends BaseTest {

  //  @Resource
  //  private RedisTemplate cacheService;


    //@Test
    public void cacheTest(){
        ItemVo itemVo=new ItemVo();
        itemVo.setCreateTime(new Date());
        itemVo.setCreateBy(super.userName);
        itemVo.setImg("空");
        itemVo.setItemCode("001");
        itemVo.setItemId(1L);
        itemVo.setStatus(new Byte("1"));
        itemVo.setItemName("商品");
        itemVo.setLastUpdateBy(super.userName);
        itemVo.setLastUpdateTime(itemVo.getCreateTime());
        itemVo.setPrice(new BigDecimal(100));

     //   this.cacheService.setObject("goods",itemVo);
    }

    @Test
    public void getCache(){
       // ItemVo itemVo=this.cacheService.getObject("goods",ItemVo.class);
       // System.out.println(itemVo);
    }
}
