package org.xfs.scm.api;

import com.alibaba.dubbo.config.annotation.Service;

@Service(timeout = 5000)
public class TestApiImpl implements TestApi {

    @Override
    public String sayHi(String name) {
        return "scm-data say: hi "+name+"!";
    }
}
