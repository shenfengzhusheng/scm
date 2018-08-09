package org.xfs.scm.base.service;

import java.io.Serializable;
import java.util.List;


import com.github.pagehelper.PageInfo;

public interface BaseServiceI<T> {
	  /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    T queryByID(Object id);
    
	  /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 查询所有数据
     * 
     * @return
     */
    List<T> queryAll();

    /**
     * 根据条件查询
     * 
     * @param t
     * @return
     */
    List<T> queryListByWhere(T t);

    /**
     * 查询数据总条数
     * 
     * @return
     */
    Integer queryCount();

    /**
     * 根据条件分页查询
     * 
     * @param t
     * @param page
     * @param rows
     * @return
     */
    PageInfo<T> queryPageByWhere(T t, Integer page, Integer rows);

    /**
     * 根据条件查询一条数据
     * 
     * @param t
     * @return
     */
    T queryOne(T t);

    /**
     * 保存
     * 
     * @param t
     */
    int save(T t);

    /**
     * 保存(忽略空字段）
     * 
     * @param t
     */
    int saveSelective(T t);

    /**
     * 更新
     * 
     * @param t
     */
    int updateById(T t);
    /**
     * 更新（忽略空字段）
     * 
     * @param t
     */
    int updateByIdSelective(T t);

    /**
     * 根据id删除
     * 
     * @param id
     */
    int deleteById(Object id);

    /**
     * 根据ids批量删除
     * 
     * @param ids
     */
    int deleteByIds(List<Object> ids);

    /**
     * 分页查询数据，排序
     * 
     * @param t
     * @param page
     * @param rows
     * @param order
     * @return
     * @throws Exception
     */
    PageInfo<T> queryListByPageAndOrder(T t, Integer page, Integer rows, String order)throws Exception;
}
