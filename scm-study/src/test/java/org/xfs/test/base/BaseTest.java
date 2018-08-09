package org.xfs.test.base;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xfs.core.business.test.service.TestService;
import org.xfs.core.platform.config.cache.redis.CacheConfig;
import org.xfs.core.platform.config.db.DataSourceConfig;
import org.xfs.core.platform.config.mq.config.MessagingListnerConfiguration;
import org.xfs.core.platform.config.mq.config.MqConfiguration;
import org.xfs.core.platform.config.spring.SpringRootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
// 用于配置spring中测试的环境
// @ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppInitializer.class, DataSourceConfig.class,
// SpringRootConfig.class, CacheConfig.class})
@ContextConfiguration(classes = {SpringRootConfig.class, DataSourceConfig.class, CacheConfig.class, MessagingListnerConfiguration.class,
        MqConfiguration.class})
public class BaseTest {

    @Resource
    private TestService testService;

    @Test
    public void testInit() {
        // for (int i = 0; i < 1000; i++) {
        // TestPo t = new TestPo();
        // int c = (i + 1);
        // // t.setId((i+1)+"");
        // t.setCode("code:" + c);
        // t.setName("name:" + c);
        // System.out.println("----------------------" + this.testService.add(t));
        //
        // }
        System.out.println("----------------------------" + this.testService.list(null, 1, 10));
    }

    // @Test
    public void testInt() {

        System.out.println("----------------------" + this.testService);

    }


}
