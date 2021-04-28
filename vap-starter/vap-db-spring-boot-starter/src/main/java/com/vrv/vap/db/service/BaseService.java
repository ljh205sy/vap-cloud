package com.vrv.vap.db.service;


import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/25 10:02
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * 插入一条数据数
     *
     * @param "对象"
     * @return 返回数据的条数
     */
    int insert(T record);

    /**
     * 修改数据
     *
     * @param record
     * @return
     */
    int update(T record);

    Integer updateSelectiveByExample(T record, Example example);

    Integer updateSelective(T record);

    T selectOne(T record);

    /**
     * 插入多条数据
     *
     * @param records
     * @return
     */
    int insertList(List<T> records);

    /**
     * 依赖example进行查询
     *
     * @param example
     * @return
     */
    List<T> findByExample(Example example);

    /** 查询所有 **/
     List<T> findAll();

    /**
     * @param "对象属性"
     * @param "对象值"
     * @param "排序字段:DESC" , 例如："id desc"        example.setOrderByClause("id DESC");
     * @return List
     */
    List<T> findByPropertyAndSort(String property, Object value, String sortOrderByClause);

    List<T> findByProperty(String property, Object value);

    /**
     * 使用主键进行删除
     * @param id 主键id
     * @return 操作的数量
     */
    Integer deleteByPrimaryKey(Object id);

    Integer deleteByIds(Class<T> clazz, String property, List<Object> values);

    Integer deleteByIds(String property, List<ID> values);

    List<T> findByProperty(Class<T> entityClass, String property, Object value);

    PageInfo<T> findPageExample(Integer page, Integer rows, Example example);

    /**
     * 按条件分页查询，返回分页对象
     * @param pageNum   第几页开始
     * @param pageSize  查询几条数据
     * @param orderBy   排序字段
     * @param example   条件查询
     * @return    返回分页对象
     */
    PageInfo<T> findPageExample(Integer pageNum, Integer pageSize, String orderBy, Example example);
    /**
     *
     * @param record 对象中含有空字段的不插入到数据库中
     * @return 操作的数量
     */
    int insertSelective(T record);

    /**
     * 通过主键ID ， 进行查询对象
     * @param id 主键ID
     * @return 对象
     */
    T findByPrimaryKey(ID id);


}