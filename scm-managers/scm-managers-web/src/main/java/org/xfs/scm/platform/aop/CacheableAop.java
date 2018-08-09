package org.xfs.scm.platform.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xfs.scm.platform.anntation.cache.RedisCache;
import org.xfs.scm.platform.service.RedisCacheService;

import javax.annotation.Resource;


@Aspect
@Component
public class CacheableAop {

    private static final Logger logger = LoggerFactory.getLogger(CacheableAop.class);
    @Resource
    RedisCacheService redisCacheUtil;

    /**
     * Service层切点 使用到了我们定义的 RedisCache 作为切点表达式。 而且我们可以看出此表达式基于 annotation。 并且用于内建属性为查询的方法之上
     */
    // @Pointcut("@annotation(com.fxmms.common.rediscache.redisannotation.RedisCache)")
    // public void redisCacheAspect() {
    // }

    @Around("@annotation(redisCache)")
    public Object cached(final ProceedingJoinPoint pjp, RedisCache redisCache) {
        String key = null;
        Object[] objs = pjp.getArgs();
        if (objs != null) {
            key = objs[0].toString();
        }
        StringBuffer buf = new StringBuffer();
        buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName()).append(":").append(key);
        String signature = pjp.getSignature().toLongString();
        String returnType = signature.split(" ")[1];
        int timeOut = redisCache.expire();
        Class<?> clazz = redisCache.type();
        // System.out.println(timeOut + ":redisCache:" + clazz.getName());

        if (logger.isDebugEnabled()) {
            logger.debug("class:" + clazz.getName());
            System.out.println("returnType:" + returnType);
            System.out.println("key:" + buf.toString());

            // 得到被代理方法的返回值类型
            // Class returnTypeClass = ((MethodSignature) pjp.getSignature()).getReturnType();
        }


        Object value = null;
        try {
            // 先从缓存获取数据
            value = this.redisCacheUtil.getObject(buf.toString(), clazz);
            if (value != null) {
                // 无缓存时直接调用程序
                this.setTimeOut(buf.toString(), timeOut);
                return value;
            }
            value = pjp.proceed(); // 跳过缓存,到后端查询数据
        } catch (Throwable e) {
            logger.error("缓存异常:{}", e);

        }

        if (value == null) {
            return value;
        }
        // 重写数据缓存时间
        this.redisCacheUtil.setObject(buf.toString(), timeOut, value);
        return value;
    }

    private void setTimeOut(String key, int seconds) {
        this.redisCacheUtil.expire(key, seconds);
    }
}
