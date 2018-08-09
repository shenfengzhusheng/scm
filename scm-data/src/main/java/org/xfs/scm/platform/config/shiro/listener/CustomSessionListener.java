package org.xfs.scm.platform.config.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.platform.config.shiro.redis.RedisSessionDao;

public class CustomSessionListener implements SessionListener {

    private final static Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    private RedisSessionDao redisSessionDao;

    @Override
    public void onStart(Session session) {
        logger.info("session [{}] is start", session.getId());
    }

    @Override
    public void onStop(Session session) {
        logger.info("session [{}] is stop", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("delete session [{}]", session.getId());
        redisSessionDao.delete(session);
    }

    public RedisSessionDao getRedisSessionDao() {
        return redisSessionDao;
    }

    public void setRedisSessionDao(RedisSessionDao redisSessionDao) {
        this.redisSessionDao = redisSessionDao;
    }
}