package org.xfs.scm.platform.config.data.mongo.dao.impl;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.xfs.scm.platform.base.model.Page;
import org.xfs.scm.platform.config.data.mongo.dao.MongoDao;
import org.xfs.scm.platform.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by 神风逐胜 on 2017/10/14 0014.22:32
 * version:1.0
 */
@Repository
public abstract  class MongoDaoImpl <T> implements MongoDao<T> {
    /**
     * spring mongodb　集成操作类　
     */
    @Resource
    protected MongoTemplate mongoTemplate;


    public T save(T entity) {
        this.mongoTemplate.insert(entity);
        return entity;
    }

    public T findById(String id) {
        return this.mongoTemplate.findById(id, this.getEntityClass());
    }

    public T findById(String id, String collectionName) {
        return this.mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    public List<T> findAll() {
        return this.mongoTemplate.findAll(this.getEntityClass());
    }

    public List<T> findAll(String collectionName) {
        return this.mongoTemplate.findAll(this.getEntityClass(), collectionName);
    }

    public List<T> find(Query query) {
        return this.mongoTemplate.find(query, this.getEntityClass());
    }
    public List<T> find(Query query,Integer page,Integer rows) {
        if(page==null){
            page=1;
        }
        if(rows==null){
            rows=20;
        }
        query.skip((page-1)*rows).limit(rows);
        return this.find(query);
    }


    public T findOne(Query query) {
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    public Page<T> findPage(Page<T> page, Query query) {
        //如果没有条件 则所有全部
        query=query==null?new Query(Criteria.where("_id").exists(true)):query;
        long count = this.count(query);
        // 总数
        page.setTotalCount((int) count);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        query.skip((currentPage - 1) * pageSize).limit(pageSize);
        List<T> rows = this.find(query);
        page.build(rows);
        return page;
    }

    public long count(Query query) {
        return this.mongoTemplate.count(query, this.getEntityClass());
    }

    public WriteResult update(Query query, Update update) {
        if (update==null) {
            return null;
        }
        return this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    public T updateOne(Query query, Update update) {
        if (update==null) {
            return null;
        }
        return this.mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    public WriteResult update(T entity) {
        Field[] fields = this.getEntityClass().getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return null;
        }
        Field idField = null;
        // 查找ID的field
        for (Field field : fields) {
            if (field.getName() != null
                    && "id".equals(field.getName().toLowerCase())) {
                idField = field;
                break;
            }
        }
        if (idField == null) {
            return null;
        }
        idField.setAccessible(true);
        String id=null;
        try {
            id = (String) idField.get(entity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (id == null || "".equals(id.trim()))
            return null;
        // 根据ID更新
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = ReflectionUtils.getUpdateObj(entity);
        if (update == null) {
            return null;
        }
        return mongoTemplate.updateFirst(query, update, getEntityClass());
    }
    public void remove(Query query) {
        this.mongoTemplate.remove(query, this.getEntityClass());
    }
    /**
     * 获得泛型类
     */
    private Class<T> getEntityClass() {
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }
}
