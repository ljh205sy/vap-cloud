package com.vrv.vap.admin.mapper;


import com.vrv.vap.admin.model.SysUserModel;
import com.vrv.vap.db.basemapper.BaseBatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wh1107066
 */
@Mapper
public interface SysUserMapper extends BaseBatisMapper<SysUserModel, Integer> {
}