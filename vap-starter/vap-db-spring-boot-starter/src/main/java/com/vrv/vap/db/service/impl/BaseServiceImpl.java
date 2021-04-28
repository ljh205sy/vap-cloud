package com.vrv.vap.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vrv.vap.db.basemapper.BaseBatisMapper;
import com.vrv.vap.db.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author wh1107066
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired(required = false)
    protected BaseBatisMapper<T, ID> baseMapper;

    private Class<T> mapperClass;

    public BaseServiceImpl() {
        // 读取泛型第一个 M 的类型，通过实现类的的第一个参数获取
        ParameterizedType t = (ParameterizedType) (getClass().getGenericSuperclass());
        if (t != null) {
            mapperClass = (Class) t.getActualTypeArguments()[0];
        }
    }

    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public int update(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer updateSelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateSelectiveByExample(T record, Example example) {
        return baseMapper.updateByExampleSelective(record, example);
    }

    @Override
    public T selectOne(T record) {
        return baseMapper.selectOne(record);
    }

    @Override
    public int insertList(List<T> records) {
        return baseMapper.insertList(records);
    }

    /**
     *   保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }


    @Override
    public List<T> findByProperty(String property, Object value) {
        if (value == null) {
            throw new RuntimeException("参数值为空，存在异常！");
        }
        Example example = new Example(mapperClass);
        return getListByCriteria(property, value, example);
    }

    @Override
    public List<T> findByProperty(Class<T> entityClass, String property, Object value) {
        Example example = new Example(entityClass);
        return getListByCriteria(property, value, example);
    }

    @Override
    public List<T> findAll() {
        return baseMapper.selectAll();
    }

    @Override
    public List<T> findByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyAndSort(String property, Object value, String sortOrderByClause) {
        Example example = new Example(mapperClass);
        // 查询结果先按`index`字段排序，如果相同，则按`AFTER_CHECK_TIME`排序
        // example.setOrderByClause("`index` ASC,`AFTER_CHECK_TIME` ASC");
        example.setOrderByClause(sortOrderByClause);
        List<T> listByCriteria = getListByCriteria(property, value, example);
        return listByCriteria;
    }

    @Override
    public PageInfo<T> findPageExample(Integer page, Integer rows, Example example) {
        PageHelper.startPage(page, rows);
        List<T> list = this.findByExample(example);
        return new PageInfo(list);
    }
    @Override
    public PageInfo<T> findPageExample(Integer pageNum, Integer pageSize, String orderBy, Example example) {
        PageHelper.startPage(pageNum, pageSize,orderBy);
        List<T> list = this.findByExample(example);
        return new PageInfo(list);
    }

    @Override
    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return this.baseMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
        return this.baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByIds(String property, List<ID> values) {
        Example example = new Example(mapperClass);
        example.createCriteria().andIn(property, values);
        return this.baseMapper.deleteByExample(example);
    }


    private List<T> getListByCriteria(String property, Object value, Example example) {
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(property, value);
        return this.findByExample(example);
    }

    @Override
    public T findByPrimaryKey(ID id) {
        return this.baseMapper.selectByPrimaryKey(id);
    }
}