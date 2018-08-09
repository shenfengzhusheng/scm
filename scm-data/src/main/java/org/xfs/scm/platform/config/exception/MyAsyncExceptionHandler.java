package org.xfs.scm.platform.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(MyAsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        logger.error("Exception message - " + throwable.getMessage());
        logger.error("Method name - " + method.getName());
        for (Object param : objects) {
            logger.error("Parameter value - " + param);
        }
    }
}
