package org.xfs.scm.data.business.study.hello.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by 神风逐胜 on 2017/10/7 0007.20:49
 * version:1.0
 */
@Service
public class HelloWorldService {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

    public String getDesc() {

        if(logger.isDebugEnabled()){
            logger.debug("getDesc() is executed!");
        }

        return "Gradle +刘治 Spring MVC Hello World Example";

    }

    public String getTitle(String name) {
        if(logger.isDebugEnabled()) {

            logger.debug("getTitle() is executed! $name : {}", name);
        }
        if(StringUtils.isEmpty(name)){
            return "Hello World";
        }else{
            return "Hello " + name;
        }

    }
}
