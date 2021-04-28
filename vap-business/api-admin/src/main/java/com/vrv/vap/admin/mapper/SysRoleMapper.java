package com.vrv.vap.admin.mapper;


import com.vrv.vap.admin.model.SysRole;
import com.vrv.vap.db.basemapper.BaseBatisMapper;

import java.util.List;

/**
 * @author wh1107066
 */
public interface SysRoleMapper extends BaseBatisMapper<SysRole, Integer> {

    /**
     * 通过用户查询角色
     * @param uid 用户id
     * @return 返回该用户下的角色列表
     */
    List<SysRole> queryRolesByUid(Integer uid);


    /**
     * 通过角色查询资源
     * @param rid 角色ID
     * @return  返回该角色下的所有资源
     */
    SysRole queryMenusByRid(Integer rid);
}