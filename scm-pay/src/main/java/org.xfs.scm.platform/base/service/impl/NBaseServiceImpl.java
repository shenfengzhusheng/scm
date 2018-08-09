package org.xfs.scm.platform.base.service.impl;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.platform.base.service.BaseServiceI;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class NBaseServiceImpl<T> implements BaseServiceI<T> {


    @Autowired
    private Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return this.mapper;
    }

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public NBaseServiceImpl() {
        super();
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;
        this.clazz = (Class<T>) ptype.getActualTypeArguments()[0];
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Transactional(readOnly=true,propagation= Propagation.NOT_SUPPORTED)
    @Override
    public T queryByID(Object id) {
        return this.getMapper().selectByPrimaryKey(id);
    }
    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public T getById(Serializable id) {
        return this.getMapper().selectByPrimaryKey(id);
    }
    /**
     * 查询所有数据
     *
     * @return
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public List<T> queryAll() {
        return this.getMapper().select(null);
    }

    /**
     * 根据条件查询
     *
     * @param t
     * @return
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public List<T> queryListByWhere(T t) {
        return this.getMapper().select(t);
    }

    /**
     * 查询数据总条数
     *
     * @return
     */

    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public Integer queryCount() {
        return this.getMapper().selectCount(null);
    }

    /**
     * 根据条件分页查询
     *
     * @param t
     * @param page
     * @param rows
     * @return
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public PageInfo<T> queryPageByWhere(T t, Integer page, Integer rows) {
        // 第一个参数是起始页，第二个参数是，页面显示的数据条数
        PageHelper.startPage(page, rows);
        List<T> list = this.getMapper().select(t);
        return new PageInfo<T>(list);
    }

    /**
     * 根据条件查询一条数据
     *
     * @param t
     * @return
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public T queryOne(T t) {
        return this.getMapper().selectOne(t);
    }

    /**
     * 保存
     *
     * @param t
     */
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public int save(T t) {

        return this.getMapper().insert(t);
    }

    /**
     * 保存(忽略空字段）
     *
     * @param t
     */
    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public int saveSelective(T t) {

        return this.getMapper().insertSelective(t);
    }

    /**
     * 更新
     *
     * @param t
     */
    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public int updateById(T t) {
        return this.getMapper().updateByPrimaryKey(t);
    }

    /**
     * 更新（忽略空字段）
     *
     * @param t
     */
    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public int updateByIdSelective(T t) {
        return this.getMapper().updateByPrimaryKeySelective(t);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public int deleteById(Object id) {
        return this.getMapper().deleteByPrimaryKey(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids
     */
    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public int deleteByIds(List<Object> ids) {
        // 设置条件
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        // 根据条件删除
        return this.getMapper().deleteByExample(example);
    }

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
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    @Override
    public PageInfo<T> queryListByPageAndOrder(T t, Integer page, Integer rows, String order)
            throws Exception {
        // 加入分页
        PageHelper.startPage(page, rows);

        // 声明一个example
        Example example = new Example(this.clazz);
        if (StringUtil.isNotBlank(order)) {
            example.setOrderByClause(order);
        }

        // 如果条件为null，直接返回
        if (t == null) {
            List<T> list = this.getMapper().selectByExample(example);
            return new PageInfo<T>(list);
        }

        // 声明条件
        Example.Criteria createCriteria = example.createCriteria();
        // 获取t的字段
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 设置为true，可以获取声明的私有字段的值
            field.setAccessible(true);
            if (field.get(t) != null) {
                // 非空的字段的值，加入到条件中
                createCriteria.andEqualTo(field.getName(), field.get(t));
            }
        }

        List<T> list = this.getMapper().selectByExample(example);
        return new PageInfo<T>(list);
    }
}
