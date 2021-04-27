package com.vrv.vap.db.basemapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.io.Serializable;

/**
 * 继承自己的MyMapper
 */
public interface BaseBatisMapper<T, ID extends Serializable> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
    // todo
    // 特别注意，这个接口不能被扫描到，否则会报错. 系统自带的baseMapper的功能比较少
}
