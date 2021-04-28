package com.vrv.vap.admin.mapper;


import com.vrv.vap.admin.model.SysUser;
import com.vrv.vap.db.basemapper.BaseBatisMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wh1107066
 */
@Mapper
public interface SysUserMapper extends BaseBatisMapper<SysUser, Integer> {
    /**
     *  通过用户返回用户的相关信息，包括角色信息
     * @param uid 用户id
     * @return  返回用户对象
     */
    SysUser queryUserAndRolesByUid(Integer uid);

    List<SysUser> queryUserAndRoles();
}