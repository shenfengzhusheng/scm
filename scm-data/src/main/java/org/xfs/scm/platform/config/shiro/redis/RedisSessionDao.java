package org.xfs.scm.platform.config.shiro.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.common.utils.SerializeUtils;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 神风逐胜 on 2018/2/4 0004.21:01
 * version:1.0
 */
public class RedisSessionDao extends AbstractSessionDAO{

    private static final Logger logger= LoggerFactory.getLogger(RedisSessionDao.class);

    private CacheServiceI cacheService;

    /**
     * shiro-redis的session对象前缀
     */

    private String keyPrefix = "shiro_redis_session:";
    /**
     * 设置缓存永不过期
     */
    private int expire=900;

    public RedisSessionDao(){}
    public RedisSessionDao(CacheServiceI cacheService,String keyPrefix){
        //System.out.println("session mapper -------------------------->"+cacheService);
        this.keyPrefix=keyPrefix;
        this.cacheService =cacheService;
    }

    /**
     * save session
     * @param session
     * @throws UnknownSessionException
     */
    private void saveSession(Session session) throws UnknownSessionException{
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }

        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        session.setTimeout(this.expire*1000);
        try {
            this.cacheService.setKey(key,this.expire,value);
          //  this.cacheService.setKey(key,this.expire,value);
        }catch (Exception e){
            logger.error("保存session失败！{}",e);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId=this.generateSessionId(session);
        this.assignSessionId(session,sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null){
            logger.error("session id is null");
            return null;
        }
        try {
            byte[] c =  this.cacheService.getKey(getByteKey(sessionId));
            if (c == null) {
                return null;
            }
            Session s = (Session) SerializeUtils.deserialize(c);
            return s;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }
        this.cacheService.del(this.getByteKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        try {
            //Set<byte[]> keys = this.cacheService.keys(this.keyPrefix + "*");
            Set<byte[]> keys= this.cacheService.keys(this.keyPrefix + "*");
            if (keys != null && keys.size() > 0) {
                for (byte[] key : keys) {
                    Session s = (Session) SerializeUtils.deserialize(this.cacheService.getKey(key));
                    sessions.add(s);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sessions;
    }


    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
    /**
     * 获得byte[]型的key
     * @return
     */
    private byte[] getByteKey(Serializable sessionId){
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

}
